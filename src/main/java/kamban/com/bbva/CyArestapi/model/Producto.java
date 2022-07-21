package kamban.com.bbva.CyArestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private Integer id;
    private String nombreProducto;
    private String descripcion;
    private LocalDate fechaIngreso;
    private boolean perecedero;
    private double precio;

}
