package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.ContinuityModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.service.ContinuityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/continuidad/api/v2")
public class ContinuityRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContinuityRestController.class);

    @Autowired
    private ContinuityService _continuityService;

    @PostMapping("/")
    public ResponseDataModel<ContinuityModel> createContinuity(@RequestBody ContinuityModel continuityData){
        ResponseDataModel<ContinuityModel> dataReturn=new ResponseDataModel<>();

        try{
            dataReturn=_continuityService.create(continuityData);

        }catch(Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API CONTINUITY-0001");

            LOGGER.error(">>>>> Error en API CONTINUITY-0001: ["+e.getMessage()+"]");
        }
        return dataReturn;
    }

    @PutMapping("/")
    public ResponseDataModel<ContinuityModel> alterContinuity(@RequestBody ContinuityModel continuityData){
        ResponseDataModel<ContinuityModel> dataReturn=new ResponseDataModel<>();

        try{
            dataReturn=_continuityService.alter(continuityData);
        }catch(Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API CONTINUITY-0002");

            LOGGER.error(">>>>> Error en API CONTINUITY-0002: ["+e.getMessage()+"]");
        }
        return dataReturn;
    }

    @DeleteMapping("/{id}")
    public ResponseDataModel<ContinuityModel> disableContinuity(@PathVariable String id){
        ResponseDataModel<ContinuityModel> dataReturn=new ResponseDataModel<>();

        try{
            dataReturn=_continuityService.disable(id);
        }catch(Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API CONTINUITY-0003");

            LOGGER.error(">>>>> Error en API CONTINUITY-0003: ["+e.getMessage()+"]");
        }
        return dataReturn;
    }

    @GetMapping("/{id}")
    public ResponseDataModel<ContinuityModel> getById(@PathVariable String id){
        ResponseDataModel<ContinuityModel> dataReturn=new ResponseDataModel<>();

        try{
            dataReturn=_continuityService.retrieveById(id);
        }catch(Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API CONTINUITY-0004");

            LOGGER.error(">>>>> Error en API CONTINUITY-0004: ["+e.getMessage()+"]");
        }
        return dataReturn;
    }

    @GetMapping("/")
    public ResponseDataModel<List<ContinuityModel>> getAll(){
        ResponseDataModel<List<ContinuityModel>> dataReturn=new ResponseDataModel<>();

        try{
            dataReturn=_continuityService.retrieveAll();
        }catch(Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API CONTINUITY-0005");

            LOGGER.error(">>>>> Error en API CONTINUITY-0005: ["+e.getMessage()+"]");
        }
        return dataReturn;
    }

}
