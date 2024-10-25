package com.ali.recipe_sharing_youtube.service;

import com.ali.recipe_sharing_youtube.Models.Recipe;
import com.ali.recipe_sharing_youtube.Models.User;
import com.ali.recipe_sharing_youtube.Repository.RecipeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImplementation implements RecipeService {

    @Autowired
    RecipeRespository recipeRespository;


    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe createdRecipe = new Recipe();
        createdRecipe.setTitle(recipe.getTitle());
        createdRecipe.setImage(recipe.getImage());
        createdRecipe.setDescription(recipe.getDescription());
        createdRecipe.setUser(user);
        createdRecipe.setCreatedAt(recipe.getCreatedAt());


        return recipeRespository.save(createdRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {
        Optional<Recipe> opt = recipeRespository.findById(id);  //optional is null save

        if (opt.isPresent()){
            return  opt.get();
        }
        throw new Exception("Recipe not found with id + " + id);


    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
            findAllRecipe();
            recipeRespository.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Recipe oldRecipe = findRecipeById(id);
        if (recipe.getTitle() != null){
            oldRecipe.setTitle(recipe.getTitle());
        }
        if (recipe.getImage() != null){
            oldRecipe.setImage(recipe.getImage());
        }
        if (recipe.getDescription() != null){
            oldRecipe.setDescription(recipe.getDescription());
        }

        return recipeRespository.save(oldRecipe);   //same is used for create and update
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRespository.findAll();
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws Exception {
        Recipe recipe = findRecipeById(recipeId);

        if (recipe.getLikes().contains(user.getId())){
            recipe.getLikes().remove(user.getId());
        }else{
            recipe.getLikes().add(user.getId());
        }
        return recipeRespository.save(recipe);
    }
}
