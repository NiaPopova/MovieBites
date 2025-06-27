package com.web.java.project.movie.bites.services;

import com.web.java.project.movie.bites.controllers.RecipeCreateDto;
import com.web.java.project.movie.bites.controllers.RecipeSummaryDto;
import com.web.java.project.movie.bites.entities.Movie;
import com.web.java.project.movie.bites.entities.recepies.Ingredient;
import com.web.java.project.movie.bites.entities.recepies.Recipe;


import com.web.java.project.movie.bites.repositories.MovieRepository;
import com.web.java.project.movie.bites.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final MovieRepository movieRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, MovieRepository movieRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.movieRepository = movieRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<RecipeSummaryDto> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(r -> new RecipeSummaryDto(
                        r.getId(),
                        r.getName(),
                        r.getDifficulty(),
                        r.getMovie().getTitle()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createRecipe(RecipeCreateDto dto) {
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id " + dto.getMovieId()));

        Recipe recipe = new Recipe();
        recipe.setName(dto.getTitle());
        recipe.setDescription(dto.getDescription());
        recipe.setSteps(dto.getSteps());
        recipe.setDifficulty("Medium");  // може да добавиш в DTO ако искаш динамично

        recipe.setMovie(movie);

        List<Ingredient> ingredients = dto.getIngredients()
                .stream()
                .map(name -> ingredientRepository.findByName(name)
                        .orElseGet(() -> {
                            Ingredient ingredient = new Ingredient();
                            ingredient.setName(name);
                            return ingredientRepository.save(ingredient);
                        })
                ).collect(Collectors.toList());

        recipe.setIngredients((Set<Ingredient>) ingredients);

        recipeRepository.save(recipe);
    }
}

