package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.MDLUser;

import java.util.List;

public interface UserService {

    public String createUser(MDLUser userData);
    public String alterUser(String userId,MDLUser userData);
    public List<MDLUser> retrieveAllUsers();
    public MDLUser retrieveUserByNetworkCode(String userId);

    public boolean existUser(String name);




//    public List<User> getUsers();
//    public User getUser(String userId);
//    public User createUser(User objUser);
//    public User updateUser(String userId, User objUser);
//    public void deleteUser(String userId);

}
