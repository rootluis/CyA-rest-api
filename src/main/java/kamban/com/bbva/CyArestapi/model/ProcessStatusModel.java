package kamban.com.bbva.CyArestapi.model;

import lombok.Data;

@Data
public class ProcessStatusModel {
    private String id;
    private String name;
    private String description;
    private boolean active;
}
