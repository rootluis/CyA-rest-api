package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.model.SprintModel;

import java.util.List;

public interface SprintService {
    public ResponseDataModel<List<SprintModel>> retrieveAll();

    public ResponseDataModel<SprintModel> retrieveById(String id);

    public ResponseDataModel<SprintModel> create(SprintModel sprintData);

    public ResponseDataModel<SprintModel> alter(SprintModel sprintData);

    public ResponseDataModel<SprintModel> disable(String id);
}
