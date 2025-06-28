package com.web.java.project.movie.bites.services;

import com.web.java.project.movie.bites.dto.RecipeCreateDto;
import com.web.java.project.movie.bites.dto.RecipeSummaryDto;

import java.util.List;

public interface RecipeService {
    List<RecipeSummaryDto> getAllRecipes();
    void createRecipe(RecipeCreateDto dto);
    RecipeSummaryDto getRecipeById(Long id);
    void updateRecipe(Long id, RecipeCreateDto dto);
    void deleteRecipe(Long id);

    List<RecipeSummaryDto> getRecipesByMovieId(Long movieId);

}

