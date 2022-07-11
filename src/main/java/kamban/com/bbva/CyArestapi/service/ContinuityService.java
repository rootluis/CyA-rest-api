package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.ContinuityModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ContinuityService {
    public ResponseDataModel<ContinuityModel> create(ContinuityModel continuityData);
    public ResponseDataModel<ContinuityModel> alter(ContinuityModel continuityData);
    public ResponseDataModel<ContinuityModel> disable(String id);
    public ResponseDataModel<List<ContinuityModel>> retrieveAll();
    public ResponseDataModel<ContinuityModel> retrieveById(String evidenceId);

}
