package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.model.TechnologyModel;

import java.util.List;

public interface TechnologyService {

    public ResponseDataModel<List<TechnologyModel>> retrieveAll();

    public ResponseDataModel<TechnologyModel> retrieveById(String id);

    public ResponseDataModel<TechnologyModel> create(TechnologyModel sprintData);

    public ResponseDataModel<TechnologyModel> alter(TechnologyModel sprintData);

    public ResponseDataModel<TechnologyModel> disable(String id);


}
