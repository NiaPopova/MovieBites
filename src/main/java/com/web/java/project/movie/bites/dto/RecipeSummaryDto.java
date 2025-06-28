package com.web.java.project.movie.bites.dto;

import java.util.List;

public class RecipeSummaryDto {

    private int id;
    private String name;
    private String summary;
    private String dificulty;
    private List<String> ingredients;
    private String movieTitle; // ✅ ново поле

    public RecipeSummaryDto() {}

    public RecipeSummaryDto(int id, String name, String summary, String dificulty, List<String> ingredients, String movieTitle) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.dificulty = dificulty;
        this.ingredients = ingredients;
        this.movieTitle = movieTitle;
    }

    // Getters и Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
