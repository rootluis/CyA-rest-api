package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.ErrorModel;
import kamban.com.bbva.CyArestapi.model.MDLArea;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.repository.AreaRepository;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.AreaService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository _areaRepo;

    private UTLGeneralService<MDLArea> _utlGeneralService;

    public AreaServiceImpl(){
        this._utlGeneralService=new UTLGeneralService<>();
    }

    @Override
    public ResponseDataModel<MDLArea> createArea(MDLArea areaData) {
        ResponseDataModel<MDLArea> dataReturn = validDataToCreate(areaData);

        if(dataReturn.getCode().equals("0")){
            if(existArea(areaData.getCode())){
                dataReturn.setCode("01");
                dataReturn.setMsn("Ya existe el area con codigo: "+areaData.getCode());
            }else{
                areaData.setActive(true); //Indicamos que el registro nacera como activo
                ENTEvidence<MDLArea> dataToSave=this._utlGeneralService.createModelEvidence(areaData);
                dataToSave.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_AREA.getValue());

                ENTEvidence<MDLArea> dataSaved=_areaRepo.save(dataToSave);
                dataSaved.getSpecificFieldsDes().setId(dataSaved.getId());

                dataReturn.setCode("0");
                dataReturn.setResult(dataSaved.getSpecificFieldsDes());
            }
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<MDLArea> alterArea(MDLArea areaData) {
        ResponseDataModel<MDLArea> dataReturn = validDataToAlter(areaData);

        if(dataReturn.getCode().equals("0")){
            if(existArea(areaData.getCode())){
                Optional<ENTEvidence<MDLArea>> entArea=_areaRepo.findById(areaData.getId());
                if(entArea.isPresent()){
                    areaData.setId(null);
                    entArea.get().setSpecificFieldsDes(areaData);
                    ENTEvidence<MDLArea> areaSaved=_areaRepo.save(entArea.get());
                    areaSaved.getSpecificFieldsDes().setId(areaSaved.getId());

                    dataReturn.setResult(areaSaved.getSpecificFieldsDes());
                    dataReturn.setCode("0");
                }
            }else {
                dataReturn.setCode("1");
                dataReturn.setMsn("El area indicada no existe");

            }
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<MDLArea> disableArea(String areaId) {
        ResponseDataModel<MDLArea> dataReturn =new ResponseDataModel<>();
        if(areaId==null || areaId.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("No se ha indicado el area a desabilitar");
        }else{
            Optional<ENTEvidence<MDLArea>> entArea = _areaRepo.findById(areaId);
            if (entArea.isPresent()) {
                entArea.get().getSpecificFieldsDes().setActive(false);

                ENTEvidence<MDLArea> areaSaved= _areaRepo.save(entArea.get());
                areaSaved.getSpecificFieldsDes().setId(areaSaved.getId());

                dataReturn.setResult(areaSaved.getSpecificFieldsDes());
                dataReturn.setCode("0");
            }else {
                dataReturn.setCode("1");
                dataReturn.setMsn("No existe el area indicada");
            }
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<List<MDLArea>> retrieveAllArea() {
        ResponseDataModel<List<MDLArea>> dataReturn =new ResponseDataModel<>();
        dataReturn.setResult(new ArrayList<>());

        List<ENTEvidence<MDLArea>> areaList=_areaRepo.findByEvidenceTypeId(UTLConstants.CODE_DOCUMENT_AREA.getValue());

        for (ENTEvidence<MDLArea> entArea: areaList) {
            entArea.getSpecificFieldsDes().setId(entArea.getId());
            dataReturn.getResult().add(entArea.getSpecificFieldsDes());
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
    public ResponseDataModel<MDLArea> retrieveAreaById(String idArea) {
        ResponseDataModel<MDLArea> dataReturn =new ResponseDataModel<>();

        if(idArea== null || idArea.isEmpty()){
            dataReturn.setCode("1");
            dataReturn.setMsn("Favor de indicar el area a inabilitar");
        }else{
            Optional<ENTEvidence<MDLArea>> areaENTEvidence=_areaRepo.findById(idArea);
            if(areaENTEvidence.isPresent()){
                areaENTEvidence.get().getSpecificFieldsDes().setId(areaENTEvidence.get().getId());

                dataReturn.setResult(areaENTEvidence.get().getSpecificFieldsDes());
                dataReturn.setCode("0");
            }else{
                dataReturn.setCode("1");
                dataReturn.setMsn("No se ha localizado el area indicada");
            }
        }

        return dataReturn;
    }

    private boolean existArea(String code) {
        ENTEvidence<MDLArea> areaENTEvidence=_areaRepo.findByCode(UTLConstants.CODE_DOCUMENT_AREA.getValue(),code);
        return areaENTEvidence != null && areaENTEvidence.getSpecificFieldsDes() != null;
    }

    private ResponseDataModel<MDLArea> validDataToCreate(MDLArea areaData){
        ResponseDataModel<MDLArea> responseData=new ResponseDataModel<>();

        if(areaData!=null){
            List<ErrorModel> errors=new ArrayList<>();

            if(areaData.getCode().isEmpty()){
                ErrorModel codeError=new ErrorModel();
                codeError.setMessage("Es necesario indicar el codigo del area");
                errors.add(codeError);
            }

            if(areaData.getName().isEmpty()){
                ErrorModel nameError=new ErrorModel();
                nameError.setMessage("Es necesario indicar el nombre del area");
                errors.add(nameError);
            }

            if(errors.size()>0){
                responseData.setCode("02");
                responseData.setMsn("Favor de validar los siguientes comentarios:");
                responseData.setErrors(errors);
            }else{
                responseData.setCode("0");
            }

        }else{
            responseData.setCode("01");
            responseData.setMsn("No se encontraron datos a procesar");
        }

        return responseData;
    }

    private ResponseDataModel<MDLArea> validDataToAlter(MDLArea areaData){
        ResponseDataModel<MDLArea> responseData=new ResponseDataModel<>();

        if(areaData!=null){
            if(areaData.getId().isEmpty()){
                responseData.setCode("1");
                responseData.setMsn("El indicador del area es incorrecto");
            }else{
                List<ErrorModel> errors=new ArrayList<>();

                if(areaData.getCode().isEmpty()){
                    ErrorModel codeError=new ErrorModel();
                    codeError.setMessage("Es necesario indicar el codigo del area");
                    errors.add(codeError);
                }

                if(areaData.getName().isEmpty()){
                    ErrorModel nameError=new ErrorModel();
                    nameError.setMessage("Es necesario indicar el nombre del area");
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
