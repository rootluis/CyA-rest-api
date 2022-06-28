package kamban.com.bbva.CyArestapi.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
public class MDLUser implements Serializable {
    private String id;
    private String networkCode;
    private String name;
    private String lastName;
    private String shortUser;
    private String email;
    private List<MDLDisciplina> disciplinas;
}
