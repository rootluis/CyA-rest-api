package kamban.com.bbva.CyArestapi.repository;

import kamban.com.bbva.CyArestapi.model.ContinuityModel;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

public interface ContinuityRepository extends EvidenceRepository<ContinuityModel> {

}
