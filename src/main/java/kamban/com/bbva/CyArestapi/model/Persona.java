package kamban.com.bbva.CyArestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    private Integer id;
    private String nombre;
    private String apPaterno;
    private LocalDate fechaNacimiento;
    private String genero;
    private String empresa;
}
