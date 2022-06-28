package kamban.com.bbva.CyArestapi.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(value = "C_MPBD_EVIDENCE")
public class ENTEvidence<T> implements Serializable {
    //YYYY-MM-DDTHH:MM:SS

    @Id
    private String id;
    private T specificFieldsDes;
    private String evidenceGroupId;
    private String evidenceTypeId;
    private Date evidenceDate;
}
