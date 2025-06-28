package com.web.java.project.movie.bites.services;

import com.web.java.project.movie.bites.dto.RecipeCreateDto;
import com.web.java.project.movie.bites.dto.RecipeSummaryDto;
import com.web.java.project.movie.bites.entities.Movie;
import com.web.java.project.movie.bites.entities.recepies.Ingredient;
import com.web.java.project.movie.bites.entities.recepies.Recipe;
import com.web.java.project.movie.bites.repositories.IngredientRepository;
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
    private final IngredientRepository ingredientRepository;
    private final MovieRepository movieRepository;

    public RecipeServiceImpl(
            RecipeRepository recipeRepository,
            MovieRepository movieRepository,
            IngredientRepository ingredientRepository
    ) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<RecipeSummaryDto> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(recipe -> new RecipeSummaryDto(
                        recipe.getId(),
                        recipe.getName(),
                        recipe.getSummary(),
                        recipe.getDifficulty(),
                        recipe.getIngredients().stream()
                                .map(Ingredient::getName)
                                .collect(Collectors.toList()),
                        recipe.getMovie() != null ? recipe.getMovie().getTitle() : null
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createRecipe(RecipeCreateDto dto) {
        Recipe recipe = new Recipe();
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setSummary(dto.getSummary());
        recipe.setDifficulty(dto.getDificulty());

        if (dto.getMovieId() != null) {
            Movie movie = movieRepository.findById(dto.getMovieId().longValue())
                    .orElseThrow(() -> new RuntimeException("Movie not found with id " + dto.getMovieId()));
            recipe.setMovie(movie);
        }

        Set<Ingredient> ingredients = dto.getIngredients().stream()
                .map(name -> ingredientRepository.findByName(name)
                        .orElseGet(() -> {
                            Ingredient ing = new Ingredient();
                            ing.setName(name);
                            return ingredientRepository.save(ing);
                        }))
                .collect(Collectors.toSet());

        recipe.setIngredients(ingredients);

        recipeRepository.save(recipe);
    }

    @Override
    public RecipeSummaryDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id " + id));

        return new RecipeSummaryDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getSummary(),
                recipe.getDifficulty(),
                recipe.getIngredients().stream()
                        .map(Ingredient::getName)
                        .collect(Collectors.toList()),
                recipe.getMovie() != null ? recipe.getMovie().getTitle() : null
        );
    }

    @Override
    public void updateRecipe(Long id, RecipeCreateDto dto) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id " + id));

        // Променяме само ако не е null
        if (dto.getName() != null) recipe.setName(dto.getName());
        if (dto.getDescription() != null) recipe.setDescription(dto.getDescription());
        if (dto.getSummary() != null) recipe.setSummary(dto.getSummary());
        if (dto.getDificulty() != null) recipe.setDifficulty(dto.getDificulty());

        if (dto.getIngredients() != null && !dto.getIngredients().isEmpty()) {
            Set<Ingredient> ingredients = dto.getIngredients().stream()
                    .map(name -> ingredientRepository.findByName(name)
                            .orElseGet(() -> {
                                Ingredient ing = new Ingredient();
                                ing.setName(name);
                                return ingredientRepository.save(ing);
                            })
                    ).collect(Collectors.toSet());

            recipe.setIngredients(ingredients);
        }

        recipeRepository.save(recipe);
    }


    @Override
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RuntimeException("Recipe with id " + id + " does not exist.");
        }
        recipeRepository.deleteById(id);
    }

    @Override
    public List<RecipeSummaryDto> getRecipesByMovieId(Long movieId) {
        List<Recipe> recipes = recipeRepository.findByMovieId(movieId);
        return recipes.stream()
                .map(recipe -> new RecipeSummaryDto(
                        recipe.getId(),
                        recipe.getName(),
                        recipe.getSummary(),
                        recipe.getDifficulty(),
                        recipe.getIngredients().stream()
                                .map(Ingredient::getName)
                                .collect(Collectors.toList()),
                        recipe.getMovie() != null ? recipe.getMovie().getTitle() : null
                ))
                .collect(Collectors.toList());
    }

}
