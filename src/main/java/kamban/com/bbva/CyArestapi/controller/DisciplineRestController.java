package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.MDLDisciplina;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.service.DisciplineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina/api/v2")
public class DisciplineRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisciplineRestController.class);

    @Autowired
    private DisciplineService _disciplineService;

    @PostMapping(value = "/")
    public ResponseDataModel<MDLDisciplina> createDisciplina(@RequestBody MDLDisciplina disciplina) {
        ResponseDataModel<MDLDisciplina> dataReturn =new ResponseDataModel<MDLDisciplina>();
        try{
            dataReturn=_disciplineService.createDiscipline(disciplina);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API DISCIPLINA-0001");

            LOGGER.error(">>>>> Error en API DISCIPLINA-0001: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @PutMapping(value = "/")
    public ResponseDataModel<MDLDisciplina> updateDisciplina(@RequestBody MDLDisciplina disciplina) {
        ResponseDataModel<MDLDisciplina> dataReturn =new ResponseDataModel<MDLDisciplina>();
        try{
            dataReturn=_disciplineService.alterDiscipline(disciplina);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API DISCIPLINA-0002");

            LOGGER.error(">>>>> Error en API DISCIPLINA-0002: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @DeleteMapping("/{idDiscipline}")
    public ResponseDataModel<MDLDisciplina> disabledDiscipline(@PathVariable String idDiscipline){
        ResponseDataModel<MDLDisciplina> dataReturn =new ResponseDataModel<MDLDisciplina>();
        try{
            dataReturn=_disciplineService.disableDiscipline(idDiscipline);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API DISCIPLINA-0003");

            LOGGER.error(">>>>> Error en API DISCIPLINA-0003: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }


    @GetMapping(value = "/")
    public ResponseDataModel<List<MDLDisciplina>> getAll(){
        ResponseDataModel<List<MDLDisciplina>> dataReturn =new ResponseDataModel<>();
        try{
            dataReturn=_disciplineService.retrieveAllDisciplina();
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API DISCIPLINA-0004");

            LOGGER.error(">>>>> Error en API DISCIPLINA-0004: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

    @GetMapping(value = "/{idDisciplina}")
    public ResponseDataModel<MDLDisciplina> getById(@PathVariable String idDisciplina){
        ResponseDataModel<MDLDisciplina> dataReturn =new ResponseDataModel<>();
        try{
            dataReturn=_disciplineService.retrieveById(idDisciplina);
        }catch (Exception e){
            dataReturn.setCode("-1");
            dataReturn.setMsn("Existe un problema interno en el API DISCIPLINA-0005");

            LOGGER.error(">>>>> Error en API DISCIPLINA-0005: ["+e.getMessage()+"]");
        }

        return dataReturn;
    }

}
