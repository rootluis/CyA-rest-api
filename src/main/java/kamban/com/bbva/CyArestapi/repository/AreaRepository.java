package kamban.com.bbva.CyArestapi.repository;

import kamban.com.bbva.CyArestapi.model.MDLArea;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AreaRepository extends EvidenceRepository<MDLArea>{
    @Query("{'evidenceTypeId' : ?0,'specificFieldsDes.code': ?1}")
    public ENTEvidence<MDLArea> findByCode(String id, String code);


}
