package com.example.Auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class welcome {



    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PreAuthorize("hasRole('USER')")    // Case Sensitive - matching to SecurityConfig User role
    @RequestMapping("/user")
    public String helloUser() {
        return "Hello user!";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin")
    public String helloAdmin() {
        return "Hello admin!";
    }



}
