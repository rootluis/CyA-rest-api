package kamban.com.bbva.CyArestapi.repository;

import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;

public interface EvidenceRepository<T> extends MongoRepository<ENTEvidence<T>, Serializable> {
    public List<ENTEvidence<T>> findByEvidenceTypeId(String evidenceTypeId);
}
