package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.User;
import kamban.com.bbva.CyArestapi.repository.UserRepository;
import kamban.com.bbva.CyArestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }

    @Override
    public User createUser(User objUser) {
        return userRepository.save(objUser);
    }

    @Override
    public User updateUser(String userId, User objUser) {
        User userFromDB = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        userFromDB.setNombre(objUser.getNombre());
        userFromDB.setApPaterno(objUser.getApPaterno());
        userFromDB.setApMaterno(objUser.getApMaterno());
        userFromDB.setUsuarioCorto(objUser.getUsuarioCorto());
        userFromDB.setEmail(objUser.getEmail());
        userFromDB.setPorcentajeAsignacion(objUser.getPorcentajeAsignacion());
        userFromDB.setPorcentajeDisponible(objUser.getPorcentajeDisponible());
        userFromDB.setTechReview(objUser.isTechReview());
        return userRepository.save(userFromDB);
    }

    @Override
    public void deleteUser(String userId) {
        User userFromDB = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        userRepository.delete(userFromDB);
    }
}
