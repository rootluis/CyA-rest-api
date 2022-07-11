package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.*;
import kamban.com.bbva.CyArestapi.repository.SprintRepository;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.SprintService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SprintServiceImpl implements SprintService {

    @Autowired
    private SprintRepository _sprintRepository;
    private UTLGeneralService<SprintModel> _utlGeneralService;

    public SprintServiceImpl(){
        this._utlGeneralService=new UTLGeneralService<>();
    }


    @Override
    public ResponseDataModel<List<SprintModel>> retrieveAll() {
        ResponseDataModel<List<SprintModel>> dataReturn =new ResponseDataModel<>();
        dataReturn.setResult(new ArrayList<>());

        List<ENTEvidence<SprintModel>> listSprint=_sprintRepository.findByEvidenceTypeId(UTLConstants.CODE_DOCUMENT_SPRINT.getValue());

        if(listSprint.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("No se han localizado datos");
        }else{
            for (ENTEvidence<SprintModel> sprintEnt: listSprint) {
                sprintEnt.getSpecificFieldsDes().setId(sprintEnt.getId());
                dataReturn.getResult().add(sprintEnt.getSpecificFieldsDes());
            }

            dataReturn.setCode("0");
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<SprintModel> retrieveById(String id) {
        ResponseDataModel<SprintModel> dataReturn=new ResponseDataModel<>();

        Optional<ENTEvidence<SprintModel>> sprintEnt=_sprintRepository.findById(id);
        if(sprintEnt.isPresent()){
            sprintEnt.get().getSpecificFieldsDes().setId(sprintEnt.get().getId());
            dataReturn.setResult(sprintEnt.get().getSpecificFieldsDes());

            dataReturn.setCode("0");
        }else {
            dataReturn.setCode("1");
            dataReturn.setMsn("No se encontro el elemento indicado");
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<SprintModel> create(SprintModel sprintData) {
        ResponseDataModel<SprintModel> dataReturn=validDataToCreate(sprintData);

        if(dataReturn.getCode().equals("0")){
            sprintData.setActive(true);
            ENTEvidence<SprintModel> sprintToSave=_utlGeneralService.createModelEvidence(sprintData);
            sprintToSave.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_SPRINT.getValue());

            ENTEvidence<SprintModel> sprintModelENTEvidence=_sprintRepository.save(sprintToSave);
            sprintModelENTEvidence.getSpecificFieldsDes().setId(sprintModelENTEvidence.getId());

            dataReturn.setResult(sprintModelENTEvidence.getSpecificFieldsDes());
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<SprintModel> alter(SprintModel sprintData) {
        ResponseDataModel<SprintModel> dataReturn =validDataToAlter(sprintData);

        if(dataReturn.getCode().equals("0")){
            Optional<ENTEvidence<SprintModel>> entToAlter=_sprintRepository.findById(sprintData.getId());

            if(entToAlter.isPresent()){
                sprintData.setId(null);
                entToAlter.get().setSpecificFieldsDes(sprintData);

                ENTEvidence<SprintModel> entToAlterSaved=_sprintRepository.save(entToAlter.get());
                entToAlterSaved.getSpecificFieldsDes().setId(entToAlterSaved.getId());

                dataReturn.setResult(entToAlterSaved.getSpecificFieldsDes());
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No existe el Sprint indicado");
            }
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<SprintModel> disable(String id) {
        ResponseDataModel<SprintModel> dataReturn =new ResponseDataModel<>();

        if(id== null || id.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("Es necesario indicar el id del Sprint a desbilitar");
        }else {
            Optional<ENTEvidence<SprintModel>> sprintENTEvidence=_sprintRepository.findById(id);

            if(sprintENTEvidence.isPresent()){
                sprintENTEvidence.get().getSpecificFieldsDes().setActive(false);

                ENTEvidence<SprintModel> sprintSaved=_sprintRepository.save(sprintENTEvidence.get());
                sprintSaved.getSpecificFieldsDes().setId(sprintSaved.getId());

                dataReturn.setResult(sprintSaved.getSpecificFieldsDes());
                dataReturn.setCode("0");
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No existe el Sprint indicada");
            }
        }

        return dataReturn;
    }


    private ResponseDataModel<SprintModel> validDataToCreate(SprintModel sprintData){
        ResponseDataModel<SprintModel> dataReturn =new ResponseDataModel<>();

        if(sprintData!=null){
            List<ErrorModel> errors=new ArrayList<>();

            if(sprintData.getName().isEmpty() ){
                ErrorModel errorName=new ErrorModel();
                errorName.setMessage("Es necesario indicar el nombre del Sprint");

                errors.add(errorName);
            }

            if(errors.size()>0){
                dataReturn.setCode("2");
                dataReturn.setMsn("Favor de validar los siguientes comentarios:");
                dataReturn.setErrors(errors);
            }else{
                dataReturn.setCode("0");
            }

        }else {
            dataReturn.setCode("1");
            dataReturn.setMsn("No se encontraron datos a procesar");
        }

        return dataReturn;
    }
    private ResponseDataModel<SprintModel> validDataToAlter(SprintModel sprintData){
        ResponseDataModel<SprintModel> dataReturn =new ResponseDataModel<>();

        if(sprintData!=null){
            if(sprintData.getId().isEmpty()){
                dataReturn.setCode("1");
                dataReturn.setMsn("El indicador del Sprint es incorrecto");
            }else{
                List<ErrorModel> errors=new ArrayList<>();

                if(sprintData.getName().isEmpty() ){
                    ErrorModel errorName=new ErrorModel();
                    errorName.setMessage("Es necesario indicar el nombre del Sprint");

                    errors.add(errorName);
                }

                if(errors.size()>0){
                    dataReturn.setCode("2");
                    dataReturn.setMsn("Favor de validar los siguientes comentarios:");
                    dataReturn.setErrors(errors);
                }else{
                    dataReturn.setCode("0");
                }
            }

        }else {
            dataReturn.setCode("1");
            dataReturn.setMsn("No se encontraron datos a procesar");
        }

        return dataReturn;
    }
}
