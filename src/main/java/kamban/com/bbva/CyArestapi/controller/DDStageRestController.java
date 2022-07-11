package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.DDStageModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.service.DDStageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stage/api/v2")
public class DDStageRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DDStageRestController.class);

    @Autowired
    private DDStageService _ddSatgeService;

    @GetMapping("/")
    public ResponseDataModel<List<DDStageModel>> retrieveAll(){
        ResponseDataModel<List<DDStageModel>> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_ddSatgeService.retrieveAll();
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API STAGE-0001");

            LOGGER.error(">>>>> Error en API STAGE-0001: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @GetMapping("/{id}")
    public ResponseDataModel<DDStageModel> retrieveById(@PathVariable String id){
        ResponseDataModel<DDStageModel> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_ddSatgeService.retrieveById(id);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API STAGE-0002");

            LOGGER.error(">>>>> Error en API STAGE-0002: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @PostMapping("/")
    public ResponseDataModel<DDStageModel> createStage(@RequestBody DDStageModel ddStageData){
        ResponseDataModel<DDStageModel> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_ddSatgeService.create(ddStageData);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API STAGE-0003");

            LOGGER.error(">>>>> Error en API STAGE-0003: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @PutMapping("/")
    public ResponseDataModel<DDStageModel> alterStage(@RequestBody DDStageModel ddStageData){
        ResponseDataModel<DDStageModel> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_ddSatgeService.alter(ddStageData);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API STAGE-0004");

            LOGGER.error(">>>>> Error en API STAGE-0004: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @DeleteMapping("/{id}")
    public ResponseDataModel<DDStageModel> disableStage(@PathVariable String id){
        ResponseDataModel<DDStageModel> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_ddSatgeService.disable(id);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API STAGE-0005");

            LOGGER.error(">>>>> Error en API STAGE-0005: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }
}
