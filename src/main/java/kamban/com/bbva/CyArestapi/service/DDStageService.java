package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.DDStageModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;

import java.util.List;

public interface DDStageService {
	public ResponseDataModel<List<DDStageModel>> retrieveAll();

	public ResponseDataModel<DDStageModel> retrieveById(String id);

	public ResponseDataModel<DDStageModel> create(DDStageModel ddStageData);

	public ResponseDataModel<DDStageModel> alter(DDStageModel ddStageData);

	public ResponseDataModel<DDStageModel> disable(String id);
}
