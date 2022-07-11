package kamban.com.bbva.CyArestapi.model;

import lombok.Data;

@Data
public class SprintModel {
    private String id;
    private String name;
    private String description;
    private boolean active;
}
