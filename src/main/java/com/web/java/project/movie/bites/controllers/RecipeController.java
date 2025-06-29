package com.web.java.project.movie.bites.controllers;

import com.web.java.project.movie.bites.dto.RecipeCreateDto;
import com.web.java.project.movie.bites.dto.RecipeSummaryDto;
import com.web.java.project.movie.bites.services.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie/bites/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping
    public List<RecipeSummaryDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeSummaryDto> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createRecipe(@RequestBody RecipeCreateDto dto) {
        recipeService.createRecipe(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRecipe(@PathVariable Long id, @RequestBody RecipeCreateDto dto) {
        recipeService.updateRecipe(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<RecipeSummaryDto>> getRecipesByMovieId(@PathVariable Long movieId) {
        return ResponseEntity.ok(recipeService.getRecipesByMovieId(movieId));
    }

}
