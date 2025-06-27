package com.web.java.project.movie.bites.dto;

public class RecipeSummaryDto {
    private Long id;
    private String title;
    private String difficulty;
    private String movieTitle;

    public RecipeSummaryDto(Long id, String title, String difficulty, String movieTitle) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.movieTitle = movieTitle;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }
}
