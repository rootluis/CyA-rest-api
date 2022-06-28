package kamban.com.bbva.CyArestapi.repository.dao;

import kamban.com.bbva.CyArestapi.model.MDLDisciplina;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface DAOEvidence extends MongoRepository<ENTEvidence<MDLDisciplina>, Serializable> {
    @Query("{'evidenceTypeId' : ?0,'specificFieldsDes.name': ?1}")
    public ENTEvidence<MDLDisciplina> findByName(String id, String name);
    public List<ENTEvidence<MDLDisciplina>> findByEvidenceTypeId(String evidenceTypeId);

}
