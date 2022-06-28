package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.Disciplina;
import kamban.com.bbva.CyArestapi.model.MDLDisciplina;
import kamban.com.bbva.CyArestapi.service.DisciplinaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina/api/v2")
public class DisciplinaRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisciplinaRestController.class);

    @Autowired
    private DisciplinaService _disciplinaService;

    @PostMapping(value = "/")
    public ResponseEntity<Object> createDisciplina(@RequestBody MDLDisciplina disciplina) {
        try{
            if(_disciplinaService.existDisciplina(disciplina.getName())){
                return new ResponseEntity<>("Ya existe la disciplina",HttpStatus.ALREADY_REPORTED);
            }else{
                String idResult=_disciplinaService.createDisciplina(disciplina);

                if(idResult!=null && idResult.length()>0){
                    return new ResponseEntity<Object>(idResult,HttpStatus.CREATED);
                }

                return new ResponseEntity<Object>(idResult,HttpStatus.NOT_MODIFIED);
            }

        }catch (Exception e){
            return new ResponseEntity<Object>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<MDLDisciplina>> getAll(){
        try {
            List<MDLDisciplina> listDisciplinas=_disciplinaService.retrieveAllDisciplina();
            if(listDisciplinas!=null && listDisciplinas.size()>0){
                return new ResponseEntity<List<MDLDisciplina>>(listDisciplinas,HttpStatus.OK);
            }else{
                return new ResponseEntity<List<MDLDisciplina>>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<MDLDisciplina> getByName(@PathVariable String name){
        try {
            MDLDisciplina disciplina=_disciplinaService.retrieveByName(name);
            if(disciplina!=null){
                return new ResponseEntity<MDLDisciplina>(disciplina,HttpStatus.OK);
            }else{
                return new ResponseEntity<MDLDisciplina>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
