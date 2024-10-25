package com.ali.recipe_sharing_youtube.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String homeController(){
        return  "Welcome Ali";
    }
}
