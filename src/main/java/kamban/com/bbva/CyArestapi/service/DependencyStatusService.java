package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.DependencyStatusModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;

import java.util.List;

public interface DependencyStatusService {
    public ResponseDataModel<List<DependencyStatusModel>> retrieveAll();
	public ResponseDataModel<DependencyStatusModel> retrieveById(String id);
	public ResponseDataModel<DependencyStatusModel> create(DependencyStatusModel dependencyStatusModel);
	public ResponseDataModel<DependencyStatusModel> alter(DependencyStatusModel dependencyStatusModel);
	public ResponseDataModel<DependencyStatusModel> disable(String id);
}
