package kamban.com.bbva.CyArestapi.repository;

import kamban.com.bbva.CyArestapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
