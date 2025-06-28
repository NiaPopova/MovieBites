package com.web.java.project.movie.bites.services;


import com.web.java.project.movie.bites.dto.MovieCreateDto;
import com.web.java.project.movie.bites.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies();
    MovieDto getMovieById(Long id);
    void createMovie(MovieCreateDto movieCreateDto);
}

