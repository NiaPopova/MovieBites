package com.web.java.project.movie.bites.dto;

import java.time.LocalDate;
import java.util.List;

public class MovieDto {
    private Long id;
    private String title;
    private String director;
    private Integer year;
    private String summary;
    private String photo;
    private String genreName;

    private List<RecipeSummaryDto> recipes;

    // Конструктор с всички полета (без рецепти)
    public MovieDto(Long id, String title, String director, Integer year,
                    String summary, String photo, String genreName) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.summary = summary;
        this.photo = photo;
        this.genreName = genreName;
    }

    // Конструктор с рецепти
    public MovieDto(Long id, String title, String director, Integer year,
                    String summary, String photo, String genreName, List<RecipeSummaryDto> recipes) {
        this(id, title, director, year, summary, photo, genreName);
        this.recipes = recipes;
    }

    // Getters и setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }


    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public String getGenreName() { return genreName; }
    public void setGenreName(String genreName) { this.genreName = genreName; }

    public List<RecipeSummaryDto> getRecipes() { return recipes; }
    public void setRecipes(List<RecipeSummaryDto> recipes) { this.recipes = recipes; }
}
