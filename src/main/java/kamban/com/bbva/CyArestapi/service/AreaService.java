package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.MDLArea;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;

import java.util.List;

public interface AreaService {
    public ResponseDataModel<MDLArea> createArea(MDLArea areaData);
    public ResponseDataModel<MDLArea> alterArea(MDLArea areaData);
    public ResponseDataModel<MDLArea> disableArea(String areaId);
    public ResponseDataModel<List<MDLArea>> retrieveAllArea();
    public ResponseDataModel<MDLArea> retrieveAreaById(String idArea);

}
