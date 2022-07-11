package kamban.com.bbva.CyArestapi.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class MDLDisciplina implements Serializable {
    private String id;
    private String name;
    private String description;
    private boolean active;
}
