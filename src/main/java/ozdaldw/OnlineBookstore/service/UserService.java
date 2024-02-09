package ozdaldw.OnlineBookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ozdaldw.OnlineBookstore.entity.User;
import ozdaldw.OnlineBookstore.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        // Hash the password before saving it
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    /*public getLoggedInUsername(){
        User user =
    }*/

}
