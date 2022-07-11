package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.model.SprintModel;
import kamban.com.bbva.CyArestapi.service.SprintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprint/api/v2")
public class SprintRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SprintRestController.class);

    @Autowired
    private SprintService _sprintService;

    @GetMapping("/")
    public ResponseDataModel<List<SprintModel>> getAll(){
        ResponseDataModel<List<SprintModel>> dataReturn=new ResponseDataModel<>();

        try{
            dataReturn=_sprintService.retrieveAll();
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API SPRINT-0001");

            LOGGER.error(">>>>> Error en API SPRINT-0001: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @GetMapping("/{id}")
    public ResponseDataModel<SprintModel> getById(@PathVariable String id){
        ResponseDataModel<SprintModel> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_sprintService.retrieveById(id);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API SPRINT-0002");

            LOGGER.error(">>>>> Error en API SPRINT-0002: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @PostMapping("/")
    public ResponseDataModel<SprintModel> createSprint(@RequestBody SprintModel sprintData){
        ResponseDataModel<SprintModel> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_sprintService.create(sprintData);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API SPRINT-0003");

            LOGGER.error(">>>>> Error en API SPRINT-0003: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @PutMapping("/")
    public ResponseDataModel<SprintModel> alterSprint(@RequestBody SprintModel sprintData){
        ResponseDataModel<SprintModel> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_sprintService.alter(sprintData);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API SPRINT-0004");

            LOGGER.error(">>>>> Error en API SPRINT-0004: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @DeleteMapping("/{id}")
    public ResponseDataModel<SprintModel> disableSprint(@PathVariable String id){
        ResponseDataModel<SprintModel> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_sprintService.disable(id);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API SPRINT-0005");

            LOGGER.error(">>>>> Error en API SPRINT-0005: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }


}
