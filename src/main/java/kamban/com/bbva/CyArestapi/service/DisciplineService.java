package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.MDLArea;
import kamban.com.bbva.CyArestapi.model.MDLDisciplina;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;

import java.util.List;

public interface DisciplineService {

    public ResponseDataModel<MDLDisciplina> createDiscipline(MDLDisciplina disciplineData);
    public ResponseDataModel<MDLDisciplina> alterDiscipline(MDLDisciplina disciplineData);
    public ResponseDataModel<MDLDisciplina> disableDiscipline(String disciplineId);
    public ResponseDataModel<List<MDLDisciplina>> retrieveAllDisciplina();
    public ResponseDataModel<MDLDisciplina> retrieveById(String idDiscipline);

}
