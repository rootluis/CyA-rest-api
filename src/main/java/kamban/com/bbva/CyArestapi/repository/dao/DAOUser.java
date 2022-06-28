package kamban.com.bbva.CyArestapi.repository.dao;

import kamban.com.bbva.CyArestapi.model.MDLUser;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface DAOUser extends MongoRepository<ENTEvidence<MDLUser>, Serializable> {
    @Query("{'evidenceTypeId' : ?0,'specificFieldsDes.networkCode': ?1}")
    public ENTEvidence<MDLUser> findByNetworkCode(String id, String networkCode);

    public List<ENTEvidence<MDLUser>> findByEvidenceTypeId(String evidenceTypeId);
}
