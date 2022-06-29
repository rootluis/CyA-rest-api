package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.MDLArea;
import kamban.com.bbva.CyArestapi.repository.dao.DAOArea;
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
    private DAOArea _daoArea;

    private UTLGeneralService<MDLArea> _utlGeneralService;

    public AreaServiceImpl(){
        this._utlGeneralService=new UTLGeneralService<>();
    }


    @Override
    public String createArea(MDLArea areaData) {

        // indicamos que el area se creara de manera activa
        areaData.setActive(true);
        ENTEvidence<MDLArea> dataToSave=this._utlGeneralService.createModelEvidence(areaData);
        dataToSave.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_AREA.getValue());

        ENTEvidence<MDLArea> dataSaved=_daoArea.save(dataToSave);

        return dataSaved.getId();
    }

    @Override
    public String alterArea(MDLArea areaData) {
        ENTEvidence<MDLArea> entArea=_daoArea.findByCode(UTLConstants.CODE_DOCUMENT_AREA.getValue(), areaData.getCode());
        if(entArea!=null && entArea.getSpecificFieldsDes()!=null){
            entArea.setSpecificFieldsDes(areaData);
            return _daoArea.save(entArea).getId();
        }
        return null;
    }

    @Override
    public List<MDLArea> retrieveAllArea() {
        List<MDLArea> dataReturn =new ArrayList<>();
        List<ENTEvidence<MDLArea>> listArea= _daoArea.findByEvidenceTypeId(UTLConstants.CODE_DOCUMENT_AREA.getValue());

        if(listArea!=null && listArea.size()>0){
            for (ENTEvidence<MDLArea> entArea:listArea) {
                if(entArea.getSpecificFieldsDes()!=null){
                    entArea.getSpecificFieldsDes().setId(entArea.getId());
                    dataReturn.add(entArea.getSpecificFieldsDes());
                }
            }
        }
        return dataReturn;
    }

    @Override
    public MDLArea retrieveAreaByCode(String code) {
        ENTEvidence<MDLArea> areaENTEvidence=_daoArea.findByCode(UTLConstants.CODE_DOCUMENT_AREA.getValue(),code);
        if(areaENTEvidence!=null && areaENTEvidence.getSpecificFieldsDes()!=null){
            areaENTEvidence.getSpecificFieldsDes().setId(areaENTEvidence.getId());
            return areaENTEvidence.getSpecificFieldsDes();
        }
        return null;
    }

    @Override
    public boolean existArea(String code) {
        ENTEvidence<MDLArea> areaENTEvidence=_daoArea.findByCode(UTLConstants.CODE_DOCUMENT_AREA.getValue(),code);
        return areaENTEvidence != null && areaENTEvidence.getSpecificFieldsDes() != null;
    }

    @Override
    public boolean disableArea(String areaId){
        Optional<ENTEvidence<MDLArea>> entArea=_daoArea.findById(areaId);

        if(entArea.isPresent()){
            entArea.get().getSpecificFieldsDes().setActive(false);
            return !_daoArea.save(entArea.get()).getId().isEmpty();
        }
        return false;
    }
}
