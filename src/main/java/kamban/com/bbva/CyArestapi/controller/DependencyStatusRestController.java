package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.DependencyStatusModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.service.DependencyStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dependencyStatus/api/v2")
public class DependencyStatusRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DependencyStatusRestController.class);

    @Autowired
    private DependencyStatusService _DependencyStatusService;

    @GetMapping("/")
    public ResponseDataModel<List<DependencyStatusModel>> retrieveAll(){
        ResponseDataModel<List<DependencyStatusModel>> dataReturn =new ResponseDataModel<>();

        try{
            dataReturn=_DependencyStatusService.retrieveAll();
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API DEPSTATUS-0001");

            LOGGER.error(">>>>> Error en API DEPSTATUS-0001: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @GetMapping("/{id}")
    public ResponseDataModel<DependencyStatusModel> retrieveById(@PathVariable String id){
        ResponseDataModel<DependencyStatusModel> dataReturn=new ResponseDataModel<>();
        try{
            dataReturn=_DependencyStatusService.retrieveById(id);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API DEPSTATUS-0002");

            LOGGER.error(">>>>> Error en API DEPSTATUS-0002: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @PostMapping("/")
    public ResponseDataModel<DependencyStatusModel> createDependencyStatus(@RequestBody DependencyStatusModel dependencyStatusModel){
        ResponseDataModel<DependencyStatusModel> dataReturn=new ResponseDataModel<>();
        try{
            dataReturn=_DependencyStatusService.create(dependencyStatusModel);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API DEPSTATUS-0003");

            LOGGER.error(">>>>> Error en API DEPSTATUS-0003: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @PutMapping("/")
    public ResponseDataModel<DependencyStatusModel> alterDependencyStatus(@RequestBody DependencyStatusModel dependencyStatusModel){
        ResponseDataModel<DependencyStatusModel> dataReturn=new ResponseDataModel<>();
        try{
            dataReturn=_DependencyStatusService.alter(dependencyStatusModel);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API DEPSTATUS-0004");

            LOGGER.error(">>>>> Error en API DEPSTATUS-0004: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @DeleteMapping("/{id}")
    public ResponseDataModel<DependencyStatusModel> disableDependencyStatus(@PathVariable String id){
        ResponseDataModel<DependencyStatusModel> dataReturn=new ResponseDataModel<>();
        try{
            dataReturn=_DependencyStatusService.disable(id);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API DEPSTATUS-0005");

            LOGGER.error(">>>>> Error en API DEPSTATUS-0005: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }
}
