package kamban.com.bbva.CyArestapi.repository.dao;

import kamban.com.bbva.CyArestapi.model.MDLArea;
import kamban.com.bbva.CyArestapi.model.MDLDisciplina;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface DAOArea extends MongoRepository<ENTEvidence<MDLArea>, Serializable> {

    @Query("{'evidenceTypeId' : ?0,'specificFieldsDes.code': ?1}")
    public ENTEvidence<MDLArea> findByCode(String id, String code);

    public List<ENTEvidence<MDLArea>> findByEvidenceTypeId(String evidenceTypeId);
}
