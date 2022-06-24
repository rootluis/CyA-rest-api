package kamban.com.bbva.CyArestapi.repository;

import kamban.com.bbva.CyArestapi.model.Disciplina;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends MongoRepository<Disciplina, String> {

    public Disciplina findBycodDisciplina(String codDisciplina);

}
