package kamban.com.bbva.CyArestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyModel {

    private String id;

    private String code;

    private String description;

    private boolean active;


}
