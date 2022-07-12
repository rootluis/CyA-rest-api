package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.model.TechnologyModel;
import kamban.com.bbva.CyArestapi.service.TechnologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/technology/api/v1")
public class TechnologyRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TechnologyRestController.class);

    @Autowired
    private TechnologyService technologyService;

    @GetMapping("/")
    public ResponseDataModel<List<TechnologyModel>> getTechnologies() {
        ResponseDataModel<List<TechnologyModel>> dataReturn = new ResponseDataModel<>();

        try {
            dataReturn = technologyService.retrieveAll();
        } catch (Exception e) {
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API SPRINT-0001");

            LOGGER.error(">>>>> Error en API SPRINT-0001: [" + e.getMessage() + "]");
        }

        return dataReturn;
    }

    @GetMapping("/{id}")
    public ResponseDataModel<TechnologyModel> getTechnologyById(@PathVariable String id) {
        ResponseDataModel<TechnologyModel> dataReturn = new ResponseDataModel<>();

        try {
            dataReturn = technologyService.retrieveById(id);
        } catch (Exception e) {
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API SPRINT-0002");

            LOGGER.error(">>>>> Error en API SPRINT-0002: [" + e.getMessage() + "]");
        }

        return dataReturn;
    }

    @PostMapping("/")
    public ResponseDataModel<TechnologyModel> createSprint(@RequestBody TechnologyModel objTechnology) {
        ResponseDataModel<TechnologyModel> dataReturn = new ResponseDataModel<>();

        try {
            dataReturn = technologyService.create(objTechnology);
        } catch (Exception e) {
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API SPRINT-0003");

            LOGGER.error(">>>>> Error en API SPRINT-0003: [" + e.getMessage() + "]");
        }

        return dataReturn;
    }

    @PutMapping("/")
    public ResponseDataModel<TechnologyModel> alterSprint(@RequestBody TechnologyModel objTechnology) {
        ResponseDataModel<TechnologyModel> dataReturn = new ResponseDataModel<>();

        try {
            dataReturn = technologyService.alter(objTechnology);
        } catch (Exception e) {
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API SPRINT-0004");

            LOGGER.error(">>>>> Error en API SPRINT-0004: [" + e.getMessage() + "]");
        }

        return dataReturn;
    }

    @DeleteMapping("/{id}")
    public ResponseDataModel<TechnologyModel> disableSprint(@PathVariable String id) {
        ResponseDataModel<TechnologyModel> dataReturn = new ResponseDataModel<>();

        try {
            dataReturn = technologyService.disable(id);
        } catch (Exception e) {
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API SPRINT-0005");

            LOGGER.error(">>>>> Error en API SPRINT-0005: [" + e.getMessage() + "]");
        }

        return dataReturn;
    }
}
