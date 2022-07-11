package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.DDStageModel;
import kamban.com.bbva.CyArestapi.model.ErrorModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.repository.DDStageRepository;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.DDStageService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DDStageServiceImpl implements DDStageService {

    @Autowired
    private DDStageRepository _stageRepository;

    private final UTLGeneralService<DDStageModel> _utlGeneralService;

    public DDStageServiceImpl(){
        this._utlGeneralService=new UTLGeneralService<>();
    }
    @Override
    public ResponseDataModel<List<DDStageModel>> retrieveAll() {
        ResponseDataModel<List<DDStageModel>> dataReturn=new ResponseDataModel<>();
        dataReturn.setResult(new ArrayList<>());

        List<ENTEvidence<DDStageModel>> ddstageList=_stageRepository.findByEvidenceTypeId(UTLConstants.CODE_DOCUMENT_STAGEDD.getValue());

        if(ddstageList.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("No se han localizado datos");
        }else{
            for (ENTEvidence<DDStageModel> stageModelENTEvidence:ddstageList) {
                stageModelENTEvidence.getSpecificFieldsDes().setId(stageModelENTEvidence.getId());
                dataReturn.getResult().add(stageModelENTEvidence.getSpecificFieldsDes());
            }

            dataReturn.setCode("0");
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<DDStageModel> retrieveById(String id) {
        ResponseDataModel<DDStageModel> dataReturn =new ResponseDataModel<>();

        if(id==null || id.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("Es necesario indicar la etapa a modificar");
        }else{
            Optional<ENTEvidence<DDStageModel>> entEvidenceStage=_stageRepository.findById(id);
            if(entEvidenceStage.isPresent()){
                entEvidenceStage.get().getSpecificFieldsDes().setId(entEvidenceStage.get().getId());

                dataReturn.setResult(entEvidenceStage.get().getSpecificFieldsDes());
                dataReturn.setCode("0");

            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No se ha localizado el registro solicitado");
            }
        }
        return dataReturn;
    }

    @Override
    public ResponseDataModel<DDStageModel> create(DDStageModel ddStageData) {
        ResponseDataModel<DDStageModel> dataReturn =validDataToCreate(ddStageData);

        if(dataReturn.getCode().equals("0")){
            ddStageData.setActive(true);
            ENTEvidence<DDStageModel> entStageToSave=_utlGeneralService.createModelEvidence(ddStageData);
            entStageToSave.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_STAGEDD.getValue());

            ENTEvidence<DDStageModel> entStageSaved=_stageRepository.save(entStageToSave);
            entStageSaved.getSpecificFieldsDes().setId(entStageSaved.getId());

            dataReturn.setResult(entStageSaved.getSpecificFieldsDes());
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<DDStageModel> alter(DDStageModel ddStageData) {
        ResponseDataModel<DDStageModel> dataReturn =validDataToAlter(ddStageData);

        if(dataReturn.getCode().equals("0")){
            Optional<ENTEvidence<DDStageModel>> stageModelENTEvidence=_stageRepository.findById(ddStageData.getId());

            if(stageModelENTEvidence.isPresent()){
                ddStageData.setId(null);
                stageModelENTEvidence.get().setSpecificFieldsDes(ddStageData);

                ENTEvidence<DDStageModel> stageAltered=_stageRepository.save(stageModelENTEvidence.get());
                stageAltered.getSpecificFieldsDes().setId(stageAltered.getId());

                dataReturn.setResult(stageAltered.getSpecificFieldsDes());
                dataReturn.setCode("0");
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No se ha localizado la etapa a modificar");
            }
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<DDStageModel> disable(String id) {
        ResponseDataModel<DDStageModel> dataReturn =new ResponseDataModel<>();

        if(id==null || id.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("Es necesario indicar la etapa a desabilitar");
        }else{
            Optional<ENTEvidence<DDStageModel>> entEvidenceStage=_stageRepository.findById(id);

            if(entEvidenceStage.isPresent()){
                entEvidenceStage.get().getSpecificFieldsDes().setActive(false);

                ENTEvidence<DDStageModel> entEvidenceStageSaved=_stageRepository.save(entEvidenceStage.get());
                entEvidenceStageSaved.getSpecificFieldsDes().setId(entEvidenceStageSaved.getId());

                dataReturn.setResult(entEvidenceStageSaved.getSpecificFieldsDes());
                dataReturn.setCode("0");
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No exite la etapa que desea modificar");
            }
        }

        return dataReturn;
    }


    private ResponseDataModel<DDStageModel> validDataToCreate(DDStageModel ddStageData){
        ResponseDataModel<DDStageModel> dataReturn =new ResponseDataModel<>();

        if(ddStageData!=null){
            List<ErrorModel> errors=new ArrayList<>();

            if(ddStageData.getName().isEmpty() ){
                ErrorModel errorName=new ErrorModel();
                errorName.setMessage("Es necesario indicar el nombre de la etapa");

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
    private ResponseDataModel<DDStageModel> validDataToAlter(DDStageModel ddStageData){
        ResponseDataModel<DDStageModel> dataReturn =new ResponseDataModel<>();

        if(ddStageData!=null){
            if(ddStageData.getId().isEmpty()){
                dataReturn.setCode("1");
                dataReturn.setMsn("Es requerido indicador de la etapa");
            }else{
                List<ErrorModel> errors=new ArrayList<>();

                if(ddStageData.getName().isEmpty() ){
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
