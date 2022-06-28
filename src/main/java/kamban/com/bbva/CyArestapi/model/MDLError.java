package kamban.com.bbva.CyArestapi.model;

import lombok.Data;

@Data
public class MDLError {
    private String message;

    public MDLError(String message){
        this.message=message;
    }
}
