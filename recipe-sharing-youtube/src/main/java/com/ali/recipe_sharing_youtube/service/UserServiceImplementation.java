package com.ali.recipe_sharing_youtube.service;

import com.ali.recipe_sharing_youtube.Models.User;
import com.ali.recipe_sharing_youtube.Repository.UserRepository;
import com.ali.recipe_sharing_youtube.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);
        if (opt.isPresent()){
            return opt.get();
        }

       throw new Exception("User not found with id " + userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);

        if (email == null){
            throw  new Exception("Provide a valid JWT token");
        }

        User user = userRepository.findByEmail(email);
        if (user == null){
            throw  new Exception("User not found with this email " + email);
        }

        return user;
    }
}
