package kamban.com.bbva.CyArestapi.utils;

import java.io.Serializable;
import java.util.List;

public interface GenericServiceRestApi<T, ID extends Serializable> {

    T save(T collection);

    void delete(ID id);

    T get(ID id);

    List<T> getAll();
}
