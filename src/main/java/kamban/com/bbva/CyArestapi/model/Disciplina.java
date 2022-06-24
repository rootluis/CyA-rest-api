package kamban.com.bbva.CyArestapi.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data 
@Document(value = "disciplina")
public class Disciplina {

    @Id
    private String id;

    private String codDisciplina;

    private String descripcion;

}
