package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.MDLArea;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/area/api/v2")
public class AreaRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisciplineRestController.class);

    @Autowired
    private AreaService _areaService;

    @PostMapping("/")
    public ResponseDataModel<MDLArea> createArea(@RequestBody MDLArea areaData){
        ResponseDataModel<MDLArea> dataReturn=new ResponseDataModel<>();
        try{
            dataReturn =_areaService.createArea(areaData);

        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API AREA-0001");

            LOGGER.error(">>>>> Error en API AREA-0001: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @PutMapping("/")
    public ResponseDataModel<MDLArea> updateArea(@RequestBody MDLArea areaData){
        ResponseDataModel<MDLArea> dataReturn =new ResponseDataModel<MDLArea>();

        try{
            dataReturn =_areaService.alterArea(areaData);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API AREA-0002");

            LOGGER.error(">>>>> Error en API AREA-0002: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @GetMapping("/")
    public ResponseDataModel<List<MDLArea>> getAll(){
        ResponseDataModel<List<MDLArea>> dataReturn=new ResponseDataModel<List<MDLArea>>();

        try {
            dataReturn=_areaService.retrieveAllArea();
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API AREA-0003");

            LOGGER.error(">>>>> Error en API AREA-0003: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @GetMapping("/{idArea}")
    public ResponseDataModel<MDLArea> getById(@PathVariable String idArea){//
        ResponseDataModel<MDLArea> dataReturn =new ResponseDataModel<>();
        try {
            dataReturn=_areaService.retrieveAreaById(idArea);
        }catch (Exception e){

            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API AREA-0004");

            LOGGER.error(">>>>> Error en API AREA-0004: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @DeleteMapping("/{id}")
    public ResponseDataModel<MDLArea> disableArea(@PathVariable String id){
        ResponseDataModel<MDLArea> dataReturn=new ResponseDataModel<>();
        try {
            dataReturn=_areaService.disableArea(id);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API AREA-0004");

            LOGGER.error(">>>>> Error en API AREA-0004: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

}
