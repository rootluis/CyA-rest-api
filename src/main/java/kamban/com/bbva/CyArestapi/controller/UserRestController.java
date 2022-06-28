package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.MDLDisciplina;
import kamban.com.bbva.CyArestapi.model.MDLUser;
import kamban.com.bbva.CyArestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user/api/v2")
public class UserRestController {

    @Autowired
    private UserService _userService;

    @PostMapping(value = "/",produces = "application/json")
    public ResponseEntity<Object> createUser(@RequestBody MDLUser userData) {
        try{
            if(_userService.existUser(userData.getNetworkCode())){
                return new ResponseEntity<>("Ya existe el usuario de red",HttpStatus.ALREADY_REPORTED);
            }else{
                String idResult=_userService.createUser(userData);

                if(idResult!=null && idResult.length()>0){
                    return new ResponseEntity<Object>(idResult,HttpStatus.CREATED);
                }

                return new ResponseEntity<Object>(idResult,HttpStatus.NOT_MODIFIED);
            }

        }catch (Exception e){
            return new ResponseEntity<Object>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/",produces = "application/json")
    public ResponseEntity<List<MDLUser>> getAll(){
        try {
            List<MDLUser> listUser=_userService.retrieveAllUsers();
            if(listUser!=null && listUser.size()>0){
                return new ResponseEntity<List<MDLUser>>(listUser,HttpStatus.OK);
            }else{
                return new ResponseEntity<List<MDLUser>>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
