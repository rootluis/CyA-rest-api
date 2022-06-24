package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.utils.GenericServiceRestApiImpl;
import kamban.com.bbva.CyArestapi.model.Disciplina;
import kamban.com.bbva.CyArestapi.repository.DisciplinaRepository;
import kamban.com.bbva.CyArestapi.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Override
    public List<Disciplina> getDisciplinas() {
        return disciplinaRepository.findAll();
    }

    @Override
    public Disciplina getDisciplina(String idDisciplina) {
        return disciplinaRepository.findById(idDisciplina)
                .orElseThrow(() -> new RuntimeException("No se encontro la disciplina: " + idDisciplina));
    }

    @Override
    public Disciplina getDisciplinaByCod(String codDisciplina) {
        return disciplinaRepository.findBycodDisciplina(codDisciplina);
    }

    @Override
    public Disciplina addDisciplina(Disciplina objdiscDisciplina) {
        return disciplinaRepository.save(objdiscDisciplina);
    }

    @Override
    public Disciplina updateDisciplina(String idDisciplina, Disciplina objDisciplina) {

        Disciplina objresult = disciplinaRepository.findById(idDisciplina)
                .orElseThrow(() -> new RuntimeException("No se actualizo la disciplina: " + idDisciplina + " debido a que no se encuentra en BD"));

        objresult.setCodDisciplina(objDisciplina.getCodDisciplina());
        objresult.setDescripcion(objDisciplina.getDescripcion());

        return disciplinaRepository.save(objresult);
    }

    @Override
    public void deleteDisciplina(String idDisciplina) {
        Disciplina objresult = disciplinaRepository.findById(idDisciplina)
                .orElseThrow(() -> new RuntimeException("No se elimino la disciplina: " + idDisciplina + " debido a que no se encuentra en BD"));
        disciplinaRepository.delete(objresult);
    }
//public class DisciplinaServiceImpl extends GenericServiceRestApiImpl<Disciplina, String> implements DisciplinaService {

//    @Autowired
//    private DisciplinaRepository disciplinaRepository;
//
//    @Override
//    public CrudRepository<Disciplina, String> getDao() {
//        return disciplinaRepository;
//    }

}
