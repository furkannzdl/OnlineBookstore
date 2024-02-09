package ozdaldw.OnlineBookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ozdaldw.OnlineBookstore.entity.User;
import ozdaldw.OnlineBookstore.service.UserService;




@RestController
@RequestMapping("/OnlineBookstore/users")
public class UserController {

    private final UserService userService;
    private User loggedUser;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestParam String username, @RequestParam String password) {
        User user = userService.createUser(username, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.loginUser(username, password);
        if (user != null) {
            user.setLogged_status(true);
            loggedUser = user;
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<User> logOut(){
        loggedUser.setLogged_status(false);
        return ResponseEntity.ok(loggedUser);
    }


    @PostMapping("/checkLoggedIn")
    public ResponseEntity<User> checkLoggedIn(){
        if(loggedUser != null && loggedUser.isLogged_status())
            return ResponseEntity.ok(loggedUser);
        return null;

    }


}

