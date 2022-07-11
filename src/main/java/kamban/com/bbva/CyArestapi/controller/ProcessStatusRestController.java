package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.ProcessStatusModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.service.ProcessStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/processStatus/api/v2")
public class ProcessStatusRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessStatusRestController.class);

    @Autowired
    private ProcessStatusService _processStatusService;

    @GetMapping("/")
    public ResponseDataModel<List<ProcessStatusModel>> retrieveAll(){
        ResponseDataModel<List<ProcessStatusModel>> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_processStatusService.retrieveAll();
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API PROCESSSTATUS-01");

            LOGGER.error(">>>>> Error en API PROCESSSTATUS-01: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @GetMapping("/{id}")
    public ResponseDataModel<ProcessStatusModel> retrieveById(@PathVariable String id){
        ResponseDataModel<ProcessStatusModel> dataReturn=new ResponseDataModel<>();
        try{
            dataReturn=_processStatusService.retrieveById(id);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API PROCESSSTATUS-02");

            LOGGER.error(">>>>> Error en API PROCESSSTATUS-02: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @PostMapping("/")
    public ResponseDataModel<ProcessStatusModel> createProcessStatus(@RequestBody ProcessStatusModel processStatusModel){
        ResponseDataModel<ProcessStatusModel> dataReturn=new ResponseDataModel<>();
        try{
            dataReturn=_processStatusService.create(processStatusModel);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API PROCESSSTATUS-03");

            LOGGER.error(">>>>> Error en API PROCESSSTATUS-03: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @PutMapping("/")
    public ResponseDataModel<ProcessStatusModel> alter(@RequestBody ProcessStatusModel processStatusModel){
        ResponseDataModel<ProcessStatusModel> dataReturn=new ResponseDataModel<>();
        try{
            dataReturn=_processStatusService.alter(processStatusModel);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API PROCESSSTATUS-04");

            LOGGER.error(">>>>> Error en API PROCESSSTATUS-04: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @DeleteMapping("/{id}")
    public ResponseDataModel<ProcessStatusModel> disable(@PathVariable String id){
        ResponseDataModel<ProcessStatusModel> dataReturn=new ResponseDataModel<>();
        try{
            dataReturn=_processStatusService.disable(id);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API PROCESSSTATUS-05");

            LOGGER.error(">>>>> Error en API PROCESSSTATUS-05: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }


}
