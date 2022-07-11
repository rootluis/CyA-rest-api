package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.ProcessStatusModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;

import java.util.List;

public interface ProcessStatusService {
    public ResponseDataModel<List<ProcessStatusModel>> retrieveAll();
	public ResponseDataModel<ProcessStatusModel> retrieveById(String id);
    public ResponseDataModel<ProcessStatusModel> create(ProcessStatusModel processStatusModel);
    public ResponseDataModel<ProcessStatusModel> alter(ProcessStatusModel processStatusModel);
    public ResponseDataModel<ProcessStatusModel> disable(String id);
}
