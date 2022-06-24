package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.Disciplina;
import kamban.com.bbva.CyArestapi.service.DisciplinaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina/api/v1")
public class DisciplinaRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisciplinaRestController.class);

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/all")
    public List<Disciplina> getAll() {
//        return disciplinaService.getAll();
        return disciplinaService.getDisciplinas();
    }

    @GetMapping("/findId/{id}")
    public Disciplina findById(@PathVariable String id) {
//        return disciplinaService.get(id);
        return disciplinaService.getDisciplina(id);
    }

    @GetMapping("/findCod/{codDisciplina}")
    public Disciplina getDisciplinaByCod(@PathVariable String codDisciplina){
        return disciplinaService.getDisciplinaByCod(codDisciplina);
    }

    @PostMapping("/save")
    public Disciplina save(@RequestBody Disciplina objDisciplina) {
        return disciplinaService.addDisciplina(objDisciplina);
    }

    @PutMapping("/{idDisciplina}")
    public Disciplina update(@PathVariable String idDisciplina, @RequestBody Disciplina objDisciplina){
        return disciplinaService.updateDisciplina(idDisciplina, objDisciplina);
    }

    @DeleteMapping("/{idDisciplina}")
    public void delete(@PathVariable String idDisciplina){
        disciplinaService.deleteDisciplina(idDisciplina);
    }

//    @PostMapping("/save")
//    public ResponseEntity<Disciplina> save(@RequestBody Disciplina objDisciplina){
//        Disciplina disciplinaResult = disciplinaService.save(objDisciplina);
//        return new ResponseEntity<Disciplina>(disciplinaResult, HttpStatus.OK);
//    }

    // Este metodo funciona muy bien, se puede tomar
//    @GetMapping("/delete/{id}")
//    public ResponseEntity<Disciplina> delete(@PathVariable Long id){
//        Disciplina disciplinaResult = disciplinaService.get(id);
//        if (disciplinaResult != null){
//            disciplinaService.delete(id);
//        }else{
//            return new ResponseEntity<Disciplina>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<Disciplina>(HttpStatus.OK);
//    }

//    @DeleteMapping("/delete/{idDisciplina}")
//    void removeDisciplina(@PathVariable Long idDisciplina) {
//        Disciplina disciplinaResult = disciplinaService.get(idDisciplina);
//        LOGGER.info(MessageFormatter.format(">>>>>[DisciplinaController] {} : {}<<<<<", "Disciplina eliminada",
//                disciplinaResult).getMessage());
//        disciplinaService.delete(disciplinaResult.getId());
//    }

}
