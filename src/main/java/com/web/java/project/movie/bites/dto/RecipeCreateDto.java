package com.web.java.project.movie.bites.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class RecipeCreateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String summary;

    @NotBlank
    private String dificulty;

    @NotNull
    private List<String> ingredients;

    @NotNull
    private Long movieId;

    public RecipeCreateDto() {}

    // Getters и Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDificulty() {
        return dificulty;
    }

    public void setDificulty(String dificulty) {
        this.dificulty = dificulty;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
