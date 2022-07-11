package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.ErrorModel;
import kamban.com.bbva.CyArestapi.model.MDLArea;
import kamban.com.bbva.CyArestapi.model.MDLDisciplina;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.repository.DisciplineRepository;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.DisciplineService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    @Autowired
    private DisciplineRepository _disciplineRepo;

    private UTLGeneralService<MDLDisciplina> _utlGeneralService;

    public DisciplineServiceImpl(){
        this._utlGeneralService=new UTLGeneralService<>();
    }

    @Override
    public ResponseDataModel<MDLDisciplina> createDiscipline(MDLDisciplina disciplineData) {
        ResponseDataModel<MDLDisciplina> dataReturn =validDataToCreate(disciplineData);

        if(dataReturn.getCode().equals("0")){
            disciplineData.setActive(true);
            ENTEvidence<MDLDisciplina> dataToSave=this._utlGeneralService.createModelEvidence(disciplineData);
            dataToSave.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_DISCIPLINA.getValue());

            ENTEvidence<MDLDisciplina> dataSaved=_disciplineRepo.save(dataToSave);
            dataSaved.getSpecificFieldsDes().setId(dataSaved.getId());

            dataReturn.setCode("0");
            dataReturn.setResult(dataSaved.getSpecificFieldsDes());
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<MDLDisciplina> alterDiscipline(MDLDisciplina disciplineData) {
        ResponseDataModel<MDLDisciplina> dataReturn =validDataToAlter(disciplineData);

        if(dataReturn.getCode().equals("0")){
            Optional<ENTEvidence<MDLDisciplina>> disciplinaENTEvidence=_disciplineRepo.findById(disciplineData.getId());

            if(disciplinaENTEvidence.isPresent()){
                disciplineData.setId(null);
                disciplinaENTEvidence.get().setSpecificFieldsDes(disciplineData);

                ENTEvidence<MDLDisciplina> disciplineSaved=_disciplineRepo.save(disciplinaENTEvidence.get());
                disciplineSaved.getSpecificFieldsDes().setId(disciplineSaved.getId());

                dataReturn.setResult(disciplineSaved.getSpecificFieldsDes());
                dataReturn.setCode("0");
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No existe la disciplina indicada");
            }
        }
        return dataReturn;
    }

    @Override
    public ResponseDataModel<MDLDisciplina> disableDiscipline(String disciplineId) {
        ResponseDataModel<MDLDisciplina> dataReturn =new ResponseDataModel<>();

        if(disciplineId== null || disciplineId.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("Es necesario indicar el id de la disciplina a desbilitar");
        }else {
            Optional<ENTEvidence<MDLDisciplina>> disciplinaENTEvidence=_disciplineRepo.findById(disciplineId);

            if(disciplinaENTEvidence.isPresent()){
                disciplinaENTEvidence.get().getSpecificFieldsDes().setActive(false);

                ENTEvidence<MDLDisciplina> disciplineSaved=_disciplineRepo.save(disciplinaENTEvidence.get());
                disciplineSaved.getSpecificFieldsDes().setId(disciplineSaved.getId());

                dataReturn.setResult(disciplineSaved.getSpecificFieldsDes());
                dataReturn.setCode("0");
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No existe la disciplina indicada");
            }
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<List<MDLDisciplina>> retrieveAllDisciplina() {
        ResponseDataModel<List<MDLDisciplina>> dataReturn =new ResponseDataModel<>();
        dataReturn.setResult(new ArrayList<>());

        List<ENTEvidence<MDLDisciplina>> disciplinaList=_disciplineRepo.findByEvidenceTypeId(UTLConstants.CODE_DOCUMENT_DISCIPLINA.getValue());

        for (ENTEvidence<MDLDisciplina> entDisciplina: disciplinaList) {
            entDisciplina.getSpecificFieldsDes().setId(entDisciplina.getId());
            dataReturn.getResult().add(entDisciplina.getSpecificFieldsDes());
        }

        if(dataReturn.getResult().size()>0){
            dataReturn.setCode("0");
        }else{
            dataReturn.setCode("1");
            dataReturn.setMsn("No se han encontrado registros");
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<MDLDisciplina> retrieveById(String idDiscipline) {
        ResponseDataModel<MDLDisciplina> dataReturn=new ResponseDataModel<>();

        if(idDiscipline==null || idDiscipline.isEmpty() ){
            dataReturn.setCode("1");
            dataReturn.setMsn("Es necesario indicar la disciplina a consultar");
        }else{
            Optional<ENTEvidence<MDLDisciplina>> disciplinaENTEvidence=_disciplineRepo.findById(idDiscipline);
            if(disciplinaENTEvidence.isPresent()){
                disciplinaENTEvidence.get().getSpecificFieldsDes().setId(disciplinaENTEvidence.get().getId());

                dataReturn.setCode("0");
                dataReturn.setResult(disciplinaENTEvidence.get().getSpecificFieldsDes());
            }
        }

        return dataReturn;
    }

    private ResponseDataModel<MDLDisciplina> validDataToCreate(MDLDisciplina disciplineData){
        ResponseDataModel<MDLDisciplina> dataReturn =new ResponseDataModel<MDLDisciplina>();

        if(disciplineData!=null){
            List<ErrorModel> listErrors=new ArrayList<>();

            if(disciplineData.getName()== null || disciplineData.getName().isEmpty() ){
                ErrorModel nameError=new ErrorModel();
                nameError.setMessage("Es necesario indicar el nombre de la disciplina");

                listErrors.add(nameError);
            }

            if(listErrors.size()>0){
                dataReturn.setCode("2");
                dataReturn.setMsn("Favor de validar los siguientes comentarios:");
            }else{
                dataReturn.setCode("0");
            }

        }else{
            dataReturn.setCode("1");
            dataReturn.setMsn("No se han identificado datos por procesar");
        }

        return dataReturn;
    }

    private ResponseDataModel<MDLDisciplina> validDataToAlter(MDLDisciplina disciplineData){
        ResponseDataModel<MDLDisciplina> responseData=new ResponseDataModel<>();

        if(disciplineData!=null){
            if(disciplineData.getId().isEmpty()){
                responseData.setCode("1");
                responseData.setMsn("El indicador del area es incorrecto");
            }else{
                List<ErrorModel> errors=new ArrayList<>();

                if(disciplineData.getName().isEmpty()){
                    ErrorModel nameError=new ErrorModel();
                    nameError.setMessage("Es necesario indicar el nombre de la disciplina");
                    errors.add(nameError);
                }

                if(errors.size()>0){
                    responseData.setCode("02");
                    responseData.setMsn("Favor de validar los siguientes comentarios:");
                    responseData.setErrors(errors);
                }else{
                    responseData.setCode("0");
                }
            }

        }else{
            responseData.setCode("01");
            responseData.setMsn("No se encontraron datos a procesar");
        }

        return responseData;
    }
}
