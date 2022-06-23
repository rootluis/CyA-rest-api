package kamban.com.bbva.CyArestapi.utils;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericServiceRestApiImpl<T, ID extends Serializable> implements GenericServiceRestApi<T, ID> {

    @Override
    public T save(T collection) {
        return getDao().save(collection);
    }

    @Override
    public void delete(ID id) {
        getDao().deleteById(id);
    }

    @Override
    public T get(ID id) {
        Optional<T> obj = getDao().findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }

        return null;
    }

    @Override
    public List<T> getAll() {
        List<T> listResult = new ArrayList<>();
        getDao().findAll().forEach(obj -> listResult.add(obj));
        return listResult;
    }

    public abstract CrudRepository<T, ID> getDao();
}
