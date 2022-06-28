package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.MDLArea;
import kamban.com.bbva.CyArestapi.model.MDLUser;

import java.util.List;

public interface AreaService {
    public String createArea(MDLArea areaData);
    public String alterArea(MDLArea areaData);
    public boolean disableArea(String areaId);
    public List<MDLArea> retrieveAllArea();
    public MDLArea retrieveAreaByCode(String code);

    public boolean existArea(String code);
}
