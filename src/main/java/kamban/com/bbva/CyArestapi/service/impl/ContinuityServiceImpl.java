package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.*;
import kamban.com.bbva.CyArestapi.repository.ContinuityRepository;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.ContinuityService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContinuityServiceImpl implements ContinuityService {

    @Autowired
    private ContinuityRepository _continuityRepo;
    private UTLGeneralService<ContinuityModel> _utlGeneralService;

    public ContinuityServiceImpl(){
        this._utlGeneralService=new UTLGeneralService<>();
    }

    @Override
    public ResponseDataModel<ContinuityModel> create(ContinuityModel continuityData) {
        ResponseDataModel<ContinuityModel> dataReturn =validDataToCreate(continuityData);

        if(dataReturn.getCode().equals("0")){
            continuityData.setActive(true);
            ENTEvidence<ContinuityModel> entToSave=_utlGeneralService.createModelEvidence(continuityData);
            entToSave.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_CONTINUITY.getValue());

            ENTEvidence<ContinuityModel> dataSaved=_continuityRepo.save(entToSave);
            dataSaved.getSpecificFieldsDes().setId(dataSaved.getId());

            dataReturn.setCode("0");
            dataReturn.setResult(dataSaved.getSpecificFieldsDes());
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<ContinuityModel> alter(ContinuityModel continuityData) {
        ResponseDataModel<ContinuityModel> dataReturn =validDataToAlter(continuityData);

        if(dataReturn.getCode().equals("0")){
            Optional<ENTEvidence<ContinuityModel>> continuityModelENTEvidence=_continuityRepo.findById(continuityData.getId());

            if(continuityModelENTEvidence.isPresent()){
                continuityData.setId(null);
                continuityModelENTEvidence.get().setSpecificFieldsDes(continuityData);

                ENTEvidence<ContinuityModel> disciplineSaved=_continuityRepo.save(continuityModelENTEvidence.get());
                disciplineSaved.getSpecificFieldsDes().setId(disciplineSaved.getId());

                dataReturn.setResult(disciplineSaved.getSpecificFieldsDes());
                dataReturn.setCode("0");
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No existe el elemento indicado");
            }
        }
        return dataReturn;    }

    @Override
    public ResponseDataModel<ContinuityModel> disable(String id) {
        ResponseDataModel<ContinuityModel> dataReturn =new ResponseDataModel<>();

        if(id== null || id.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("Es necesario indicar el id del elemento a desbilitar");
        }else {
            Optional<ENTEvidence<ContinuityModel>> continuityENTEvidence=_continuityRepo.findById(id);

            if(continuityENTEvidence.isPresent()){
                continuityENTEvidence.get().getSpecificFieldsDes().setActive(false);

                ENTEvidence<ContinuityModel> continuitySaved=_continuityRepo.save(continuityENTEvidence.get());
                continuitySaved.getSpecificFieldsDes().setId(continuitySaved.getId());

                dataReturn.setResult(continuitySaved.getSpecificFieldsDes());
                dataReturn.setCode("0");
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No existe el elemento indicada");
            }
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<List<ContinuityModel>> retrieveAll() {
        ResponseDataModel<List<ContinuityModel>> dataReturn =new ResponseDataModel<>();
        dataReturn.setResult(new ArrayList<>());

        List<ENTEvidence<ContinuityModel>> continuityList=_continuityRepo.findByEvidenceTypeId(UTLConstants.CODE_DOCUMENT_CONTINUITY.getValue());

        if(continuityList.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("No se han encontrado registros");
        }else{
            for (ENTEvidence<ContinuityModel> entContinuity:continuityList) {
                entContinuity.getSpecificFieldsDes().setId(entContinuity.getId());
                dataReturn.getResult().add(entContinuity.getSpecificFieldsDes());
            }
            dataReturn.setCode("0");
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<ContinuityModel> retrieveById(String evidenceId) {
        ResponseDataModel<ContinuityModel> dataReturn =new ResponseDataModel<>();
        Optional<ENTEvidence<ContinuityModel>> entContinuity=_continuityRepo.findById(evidenceId);
        if(entContinuity.isPresent()){
            entContinuity.get().getSpecificFieldsDes().setId(entContinuity.get().getId());
            dataReturn.setCode("0");
            dataReturn.setResult(entContinuity.get().getSpecificFieldsDes());
        }else{
            dataReturn.setCode("0");
            dataReturn.setMsn("No se localiso el elemento solicitado");
        }

        return dataReturn;
    }


    private ResponseDataModel<ContinuityModel> validDataToCreate(ContinuityModel continuityData){
        ResponseDataModel<ContinuityModel> dataReturn =new ResponseDataModel<>();

        if(continuityData!=null){
            List<ErrorModel> errors=new ArrayList<>();

            if(continuityData.getName().isEmpty() ){
                ErrorModel errorName=new ErrorModel();
                errorName.setMessage("Es necesario indicar el nombre de la continuidad");

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
    private ResponseDataModel<ContinuityModel> validDataToAlter(ContinuityModel continuityData){
        ResponseDataModel<ContinuityModel> dataReturn =new ResponseDataModel<>();

        if(continuityData!=null){
            if(continuityData.getId().isEmpty()){
                dataReturn.setCode("1");
                dataReturn.setMsn("El indicador de la continuidad es incorrecto");
            }else{
                List<ErrorModel> errors=new ArrayList<>();

                if(continuityData.getName().isEmpty() ){
                    ErrorModel errorName=new ErrorModel();
                    errorName.setMessage("Es necesario indicar el nombre de la continuidad");

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
