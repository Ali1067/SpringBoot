package com.ali.recipe_sharing_youtube.Repository;

import com.ali.recipe_sharing_youtube.Models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRespository extends JpaRepository<Recipe, Long> {
}
