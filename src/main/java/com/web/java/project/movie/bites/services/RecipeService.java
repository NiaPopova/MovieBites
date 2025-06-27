package com.web.java.project.movie.bites.services;

import com.web.java.project.movie.bites.controllers.RecipeCreateDto;
import com.web.java.project.movie.bites.controllers.RecipeSummaryDto;

import java.util.List;

public interface RecipeService {
    List<RecipeSummaryDto> getAllRecipes();
    void createRecipe(RecipeCreateDto dto);
}

