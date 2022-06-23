package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.utils.GenericServiceRestApiImpl;
import kamban.com.bbva.CyArestapi.model.Disciplina;
import kamban.com.bbva.CyArestapi.repository.DisciplinaRepository;
import kamban.com.bbva.CyArestapi.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaServiceImpl extends GenericServiceRestApiImpl<Disciplina, Long> implements DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Override
    public CrudRepository<Disciplina, Long> getDao() {
        return disciplinaRepository;
    }

}
