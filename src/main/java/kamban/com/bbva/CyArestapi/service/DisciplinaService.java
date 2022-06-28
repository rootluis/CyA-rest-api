package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.MDLDisciplina;

import java.util.List;

public interface DisciplinaService {

    public String createDisciplina(MDLDisciplina disciplinaData);

    public List<MDLDisciplina> retrieveAllDisciplina();
    public MDLDisciplina retrieveByName(String name);

    public boolean existDisciplina(String name);

}
