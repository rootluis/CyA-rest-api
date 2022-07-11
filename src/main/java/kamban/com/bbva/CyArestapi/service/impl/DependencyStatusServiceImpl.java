package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.DependencyStatusModel;
import kamban.com.bbva.CyArestapi.model.ErrorModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.repository.DependencyStatusRepository;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.DependencyStatusService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DependencyStatusServiceImpl implements DependencyStatusService {

    @Autowired
    private DependencyStatusRepository _dependenDependencyStatusRepository;

    private UTLGeneralService<DependencyStatusModel> _utlGeneralService;

    public DependencyStatusServiceImpl(){
        this._utlGeneralService=new UTLGeneralService<>();
    }

    @Override
    public ResponseDataModel<List<DependencyStatusModel>> retrieveAll() {
        ResponseDataModel<List<DependencyStatusModel>> dataReturn = new ResponseDataModel<>();
        dataReturn.setResult(new ArrayList<>());

        List<ENTEvidence<DependencyStatusModel>> entEvidences=_dependenDependencyStatusRepository.findByEvidenceTypeId(UTLConstants.CODE_DOCUMENT_DEPENDENCY_STATUS.getValue());

        if(entEvidences.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("No se han localizado datos");
        }else{
            for (ENTEvidence<DependencyStatusModel> entEvidence:entEvidences) {
                entEvidence.getSpecificFieldsDes().setId(entEvidence.getId());
                dataReturn.getResult().add(entEvidence.getSpecificFieldsDes());
            }

            dataReturn.setCode("0");
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<DependencyStatusModel> retrieveById(String id) {
        ResponseDataModel<DependencyStatusModel> dataReturn =new ResponseDataModel<>();

        Optional<ENTEvidence<DependencyStatusModel>> entEvidence=_dependenDependencyStatusRepository.findById(id);
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
    public ResponseDataModel<DependencyStatusModel> create(DependencyStatusModel dependencyStatusModel) {
        ResponseDataModel<DependencyStatusModel> dataReturn=validDataToCreate(dependencyStatusModel);

        if(dataReturn.getCode().equals("0")){
            dependencyStatusModel.setActive(true);
            ENTEvidence<DependencyStatusModel> entEvidenceToSave=_utlGeneralService.createModelEvidence(dependencyStatusModel);
            entEvidenceToSave.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_DEPENDENCY_STATUS.getValue());

            ENTEvidence<DependencyStatusModel> entEvidenceSaved=_dependenDependencyStatusRepository.save(entEvidenceToSave);
            entEvidenceSaved.getSpecificFieldsDes().setId(entEvidenceSaved.getId());

            dataReturn.setResult(entEvidenceSaved.getSpecificFieldsDes());
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<DependencyStatusModel> alter(DependencyStatusModel dependencyStatusModel) {
        ResponseDataModel<DependencyStatusModel> dataReturn=validDataToAlter(dependencyStatusModel);

        if(dataReturn.getCode().equals("0")){
            Optional<ENTEvidence<DependencyStatusModel>> entEvidence=_dependenDependencyStatusRepository.findById(dependencyStatusModel.getId());
            if(entEvidence.isPresent()){
                dependencyStatusModel.setId(null);
                entEvidence.get().setSpecificFieldsDes(dependencyStatusModel);

                ENTEvidence<DependencyStatusModel> entEvidenceSaved=_dependenDependencyStatusRepository.save(entEvidence.get());
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
    public ResponseDataModel<DependencyStatusModel> disable(String id) {
        ResponseDataModel<DependencyStatusModel> dataReturn=new ResponseDataModel<>();

        if(id==null || id.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("Es necesario el indicador del Estatus");
        }else{
            Optional<ENTEvidence<DependencyStatusModel>> entEvidence=_dependenDependencyStatusRepository.findById(id);
            if(entEvidence.isPresent()){
                entEvidence.get().getSpecificFieldsDes().setActive(false);

                ENTEvidence<DependencyStatusModel> entEvidenceAltered=_dependenDependencyStatusRepository.save(entEvidence.get());
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


    private ResponseDataModel<DependencyStatusModel> validDataToCreate(DependencyStatusModel dependencyStatusData){
        ResponseDataModel<DependencyStatusModel> dataReturn =new ResponseDataModel<>();

        if(dependencyStatusData!=null){
            List<ErrorModel> errors=new ArrayList<>();

            if(dependencyStatusData.getName().isEmpty() ){
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
    private ResponseDataModel<DependencyStatusModel> validDataToAlter(DependencyStatusModel dependencyStatusData){
        ResponseDataModel<DependencyStatusModel> dataReturn =new ResponseDataModel<>();

        if(dependencyStatusData!=null){
            if(dependencyStatusData.getId().isEmpty()){
                dataReturn.setCode("1");
                dataReturn.setMsn("El indicador del estatus es incorrecto");
            }else{
                List<ErrorModel> errors=new ArrayList<>();

                if(dependencyStatusData.getName().isEmpty() ){
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
