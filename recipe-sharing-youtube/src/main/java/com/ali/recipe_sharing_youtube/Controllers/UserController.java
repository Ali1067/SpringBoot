package com.ali.recipe_sharing_youtube.Controllers;

import com.ali.recipe_sharing_youtube.Models.User;
import com.ali.recipe_sharing_youtube.Repository.UserRepository;
import com.ali.recipe_sharing_youtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

@Autowired
    UserService userService;

@GetMapping("/api/user/profile")
public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {

    User user = userService.findUserByJwt(jwt);
    return user;
}











    //initial apis
    @Autowired
    UserRepository userRepository;
//    @PostMapping("/users")
//    public User createUser(@RequestBody User user) throws Exception {     //user is something which will  get from client side
//        User isExist = userRepository.findByEmail(user.getEmail());
//        if (isExist != null){
//            throw new Exception("User already exist" + user.getEmail());
//        }
//        User savedUser = userRepository.save(user);     //data will be stored into database
//        return  savedUser;  //something which will come as response on our postman
//    }

//    @DeleteMapping("/users/{userId}")
//    public String deleteUser(@PathVariable Long userId) throws Exception {     //user is something which will  get from client side
//         userRepository.deleteById(userId);
//         return userId + " Deleted Successfully";
//    }

//    @GetMapping("/users")
//    public List<User> deleteUser() throws Exception {     //user is something which will  get from client side
//        List<User> users = userRepository.findAll();
//        return users;
//    }



}
