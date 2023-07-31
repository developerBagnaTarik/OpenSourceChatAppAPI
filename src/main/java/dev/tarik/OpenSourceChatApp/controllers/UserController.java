package dev.tarik.OpenSourceChatApp.controllers;

import dev.tarik.OpenSourceChatApp.models.User;
import dev.tarik.OpenSourceChatApp.requests.UpdateTheAvatarOfUserRequest;
import dev.tarik.OpenSourceChatApp.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class UserController {

    private final UserService userService;
    private static final String ENTITY_PATH = "User";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ENTITY_PATH+"/users")
    public ResponseEntity<?> findUsers(){
        List<User> allUsersOfTheServiceResponse = userService.findUsers();
        Map<String,Object> response = new HashMap<>();
        response.put("status_code",HttpStatus.OK.value());
        response.put("data",allUsersOfTheServiceResponse);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(ENTITY_PATH+"/usersThatAreDifferentFrom/{id}")
    public ResponseEntity<?> findUsersDifferentOf(@PathVariable ObjectId id){
        List<User> allUsersOfTheServiceResponse = userService.findUsersThatAreDifferentFrom(id);
        Map<String,Object> response = new HashMap<>();
        response.put("status_code",HttpStatus.OK.value());
        response.put("data",allUsersOfTheServiceResponse);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(ENTITY_PATH+"/add")
    public ResponseEntity<?> add(@RequestBody User user){
        User userOfTheServiceResponse = userService.add(user);
        if(userOfTheServiceResponse != null){
            Map<String, Object> response = new HashMap<>();
            response.put("status_code",HttpStatus.OK.value());
            response.put("data",userOfTheServiceResponse);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("The user with this username already exists");
    }

    @PutMapping(ENTITY_PATH+"/users/updateTheAvatar")
    public ResponseEntity<?> updateTheAvatarUrl(@RequestBody UpdateTheAvatarOfUserRequest updateTheAvatarOfUserRequest){
        User userOfTheServiceResponse = userService.updateTheAvatarUrl(updateTheAvatarOfUserRequest);
        if(userOfTheServiceResponse != null){
            Map<String, Object> response = new HashMap<>();
            response.put("status_code",HttpStatus.OK.value());
            response.put("data",userOfTheServiceResponse);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user does not exists");
    }
}
