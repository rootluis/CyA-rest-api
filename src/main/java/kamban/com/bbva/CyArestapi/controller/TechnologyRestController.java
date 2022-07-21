package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.TechnologyModel;
import kamban.com.bbva.CyArestapi.service.TechnologyService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/technology/api/v1")
public class TechnologyRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TechnologyRestController.class);

    @Autowired
    private TechnologyService technologyService;

    @GetMapping("/technologies")
    public List<TechnologyModel> getTechnologies() {
        LOGGER.info("Ejecutando servicio - /technology/api/v1/technologies");
        List<TechnologyModel> listresult = technologyService
                .retrieveAllTechnology(UTLConstants.CODE_DOCUMENT_DEPENDENCY_TECHNOLOGY.getValue());
        LOGGER.info("Tecnologias recuperadas: " + listresult);
        return listresult;
    }

    @GetMapping("/{id}")
    public TechnologyModel getTechnologyById(@PathVariable String id) {
        return technologyService.retrieveTechnologyById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public TechnologyModel addTechnology(@RequestBody TechnologyModel objTechnology) {
        LOGGER.info("Tecnologia a crear: " + objTechnology);
        return technologyService.createTechnology(objTechnology);
    }

    @PutMapping("/{id}")
    public TechnologyModel updateTechnology(@PathVariable String id, @RequestBody TechnologyModel objTechnology) {
        LOGGER.info("Tecnologia a actualizar: " + objTechnology);
        return technologyService.updateTechnology(id, objTechnology);
    }

    @DeleteMapping("/{id}")
    public TechnologyModel tecnologyOnOrOff(@PathVariable String id) {
       return technologyService.disableTechnology(id);
    }

    @DeleteMapping("/delete/{id}")
    public TechnologyModel deleteTechnology(@PathVariable String id){
        return technologyService.deleteTechnology(id);
    }
}
