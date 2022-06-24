package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.utils.GenericServiceRestApi;
import kamban.com.bbva.CyArestapi.model.Disciplina;

import java.util.List;

public interface DisciplinaService {
//public interface DisciplinaService extends GenericServiceRestApi<Disciplina, Long> {

    public List<Disciplina> getDisciplinas();

    public Disciplina getDisciplina(String idDisciplina);

    public Disciplina getDisciplinaByCod(String codDisciplina);

    public Disciplina addDisciplina(Disciplina objdiscDisciplina);

    public Disciplina updateDisciplina(String idDisciplina, Disciplina objDisciplina);

    public void deleteDisciplina(String idDisciplina);
}
