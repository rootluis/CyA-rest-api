package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers();

    public User getUser(String userId);

    public User createUser(User objUser);

    public User updateUser(String userId, User objUser);

    public void deleteUser(String userId);

}
