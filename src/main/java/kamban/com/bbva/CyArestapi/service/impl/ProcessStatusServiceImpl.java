package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.ErrorModel;
import kamban.com.bbva.CyArestapi.model.ProcessStatusModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.repository.ProcessStatusrepository;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.ProcessStatusService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessStatusServiceImpl implements ProcessStatusService {

    @Autowired
    private ProcessStatusrepository _processStatusrepository;

    private UTLGeneralService<ProcessStatusModel> _utlGeneralService;

    public ProcessStatusServiceImpl(){
        this._utlGeneralService=new UTLGeneralService<>();
    }

    @Override
    public ResponseDataModel<List<ProcessStatusModel>> retrieveAll() {
        ResponseDataModel<List<ProcessStatusModel>> dataReturn = new ResponseDataModel<>();
        dataReturn.setResult(new ArrayList<>());

        List<ENTEvidence<ProcessStatusModel>> entEvidences=_processStatusrepository.findByEvidenceTypeId(UTLConstants.CODE_DOCUMENT_PROCESS_STATUS.getValue());

        if(entEvidences.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("No se han localizado datos");
        }else{
            for (ENTEvidence<ProcessStatusModel> entEvidence:entEvidences) {
                entEvidence.getSpecificFieldsDes().setId(entEvidence.getId());
                dataReturn.getResult().add(entEvidence.getSpecificFieldsDes());
            }

            dataReturn.setCode("0");
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<ProcessStatusModel> retrieveById(String id) {
        ResponseDataModel<ProcessStatusModel> dataReturn =new ResponseDataModel<>();

        Optional<ENTEvidence<ProcessStatusModel>> entEvidence=_processStatusrepository.findById(id);
        if(entEvidence.isPresent()){
            entEvidence.get().getSpecificFieldsDes().setId(entEvidence.get().getId());

            dataReturn.setResult(entEvidence.get().getSpecificFieldsDes());
            dataReturn.setCode("0");
        }else{
            dataReturn.setCode("1");
            dataReturn.setMsn("No se ha localizado el estatus solicitado");
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<ProcessStatusModel> create(ProcessStatusModel processStatusModel) {
        ResponseDataModel<ProcessStatusModel> dataReturn=validDataToCreate(processStatusModel);

        if(dataReturn.getCode().equals("0")){
            processStatusModel.setActive(true);
            ENTEvidence<ProcessStatusModel> entEvidenceToSave=_utlGeneralService.createModelEvidence(processStatusModel);
            entEvidenceToSave.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_PROCESS_STATUS.getValue());

            ENTEvidence<ProcessStatusModel> entEvidenceSaved=_processStatusrepository.save(entEvidenceToSave);
            entEvidenceSaved.getSpecificFieldsDes().setId(entEvidenceSaved.getId());

            dataReturn.setResult(entEvidenceSaved.getSpecificFieldsDes());
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<ProcessStatusModel> alter(ProcessStatusModel processStatusModel) {
        ResponseDataModel<ProcessStatusModel> dataReturn=validDataToAlter(processStatusModel);

        if(dataReturn.getCode().equals("0")){
            Optional<ENTEvidence<ProcessStatusModel>> entEvidence=_processStatusrepository.findById(processStatusModel.getId());
            if(entEvidence.isPresent()){
                processStatusModel.setId(null);
                entEvidence.get().setSpecificFieldsDes(processStatusModel);

                ENTEvidence<ProcessStatusModel> entEvidenceSaved=_processStatusrepository.save(entEvidence.get());
                entEvidenceSaved.getSpecificFieldsDes().setId(entEvidenceSaved.getId());

                dataReturn.setResult(entEvidenceSaved.getSpecificFieldsDes());
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No se ha localozado el registro");
            }
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<ProcessStatusModel> disable(String id) {
        ResponseDataModel<ProcessStatusModel> dataReturn=new ResponseDataModel<>();

        if(id==null || id.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("Es necesario el indicador del Estatus");
        }else{
            Optional<ENTEvidence<ProcessStatusModel>> entEvidence=_processStatusrepository.findById(id);
            if(entEvidence.isPresent()){
                entEvidence.get().getSpecificFieldsDes().setActive(false);

                ENTEvidence<ProcessStatusModel> entEvidenceAltered=_processStatusrepository.save(entEvidence.get());
                entEvidenceAltered.getSpecificFieldsDes().setId(entEvidenceAltered.getId());

                dataReturn.setResult(entEvidenceAltered.getSpecificFieldsDes());
                dataReturn.setCode("0");
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No existe el estatus seleccionado");
            }
        }

        return dataReturn;
    }


    private ResponseDataModel<ProcessStatusModel> validDataToCreate(ProcessStatusModel processStatusData){
        ResponseDataModel<ProcessStatusModel> dataReturn =new ResponseDataModel<>();

        if(processStatusData!=null){
            List<ErrorModel> errors=new ArrayList<>();

            if(processStatusData.getName().isEmpty() ){
                ErrorModel errorName=new ErrorModel();
                errorName.setMessage("Es necesario indicar el nombre del estatus");

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
    private ResponseDataModel<ProcessStatusModel> validDataToAlter(ProcessStatusModel processStatusData){
        ResponseDataModel<ProcessStatusModel> dataReturn =new ResponseDataModel<>();

        if(processStatusData!=null){
            if(processStatusData.getId().isEmpty()){
                dataReturn.setCode("1");
                dataReturn.setMsn("El indicador del estatus es incorrecto");
            }else{
                List<ErrorModel> errors=new ArrayList<>();

                if(processStatusData.getName().isEmpty() ){
                    ErrorModel errorName=new ErrorModel();
                    errorName.setMessage("Es necesario indicar el nombre del estatus");

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
