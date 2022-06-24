package kamban.com.bbva.CyArestapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.util.List;

@Data
@Document(value = "user")
public class User {

    @Id
    private String usuarioRed;

    private String nombre;

    private String apPaterno;

    private String apMaterno;

    private String usuarioCorto;

    private String email;

    private int porcentajeAsignacion;

    private int porcentajeDisponible;

    private boolean techReview;

    private List<Disciplina> disciplinas;

}
