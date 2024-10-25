package com.ali.recipe_sharing_youtube.Controllers;

import com.ali.recipe_sharing_youtube.Models.Recipe;
import com.ali.recipe_sharing_youtube.Models.User;
import com.ali.recipe_sharing_youtube.Repository.UserRepository;
import com.ali.recipe_sharing_youtube.service.RecipeService;
import com.ali.recipe_sharing_youtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeControlelr {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userservice;

//    @PostMapping("/user/{userId}")
//    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception {
@PostMapping()
public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userservice.findUserByJwt(jwt);
        Recipe createRecipe = recipeService.createRecipe(recipe,user);
        return  createRecipe;
    }

    @PutMapping("/{Id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long Id) throws Exception {
        Recipe updateRecipe = recipeService.createRecipe(recipe,null);
        return  updateRecipe;
    }

    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        User user = userservice.findUserByJwt(jwt);
        Recipe updateRecipe = recipeService.likeRecipe(id,user);
        return  updateRecipe;
    }

    @GetMapping()
    public List<Recipe> getAllRecipe() throws Exception {
        List<Recipe> recipes =  recipeService.findAllRecipe();
        return  recipes;
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipe(recipeId);
        return  "recipes deleted successfully";
    }
}
