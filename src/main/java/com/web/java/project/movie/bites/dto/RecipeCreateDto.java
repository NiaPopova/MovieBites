package com.web.java.project.movie.bites.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class RecipeCreateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private List<String> ingredients;

    @NotBlank
    private String steps;

    @NotNull
    private Long movieId;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getIngredients() { return ingredients; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }
    public String getSteps() { return steps; }
    public void setSteps(String steps) { this.steps = steps; }
    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
}

