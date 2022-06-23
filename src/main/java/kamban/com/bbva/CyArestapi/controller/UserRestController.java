package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.User;
import kamban.com.bbva.CyArestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manegement/api/v1/user")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return userService.getUser(userId);
    }

    @PostMapping("")
    public User addUser(@RequestBody User objUser){
        return userService.createUser(objUser);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User objUser){
        return userService.updateUser(userId, objUser);
    }

    @DeleteMapping("/{userId}")
    public void removeUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }
}
