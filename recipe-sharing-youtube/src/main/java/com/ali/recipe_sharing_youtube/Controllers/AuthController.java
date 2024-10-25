package com.ali.recipe_sharing_youtube.Controllers;

import com.ali.recipe_sharing_youtube.Models.User;
import com.ali.recipe_sharing_youtube.Repository.UserRepository;
import com.ali.recipe_sharing_youtube.config.JwtProvider;
import com.ali.recipe_sharing_youtube.request.LoginRequest;
import com.ali.recipe_sharing_youtube.response.AuthResponse;
import com.ali.recipe_sharing_youtube.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerUserDetailsService customeruserdetails;
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{

        String email = user.getEmail();
        String password = user.getPassword();
        String fullname = user.getFullName();


        User isExistEmail = userRepository.findByEmail(email);

        if (isExistEmail !=null){
            throw  new Exception("Email is already used with another account.");
        }

        User createduser = new User();
        createduser.setEmail(email);

        createduser.setFullName(fullname);


        String encodedPassword =   passwordEncoder.encode(password);
        createduser.setPassword(encodedPassword);
        User savedUser = userRepository.save(createduser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("Signup Success");

        return res;
    }


    @PostMapping("/signin")
    public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("Signup Success");
        System.out.println("Token is "+ token);
        return res;
    }

    private Authentication authenticate(String username, String password) {
     UserDetails userdetails = customeruserdetails.loadUserByUsername(username);


     if (userdetails==null){
         throw new BadCredentialsException("User not found");
     }
     if (!passwordEncoder.matches(password,userdetails.getPassword())){
         throw new BadCredentialsException("invalid password");

     }

     return  new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());

    }

}
