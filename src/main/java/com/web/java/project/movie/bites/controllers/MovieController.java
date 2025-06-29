package com.web.java.project.movie.bites.controllers;

import com.web.java.project.movie.bites.dto.MovieCreateDto;
import com.web.java.project.movie.bites.dto.MovieDto;
import com.web.java.project.movie.bites.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie/bites/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // GET /api/movies - Връща всички филми
    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    // GET /api/movies/{id} - Връща конкретен филм по ID
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        MovieDto movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    // POST /api/movies - Добавя нов филм
    @PostMapping
    public ResponseEntity<Void> createMovie(@Valid @RequestBody MovieCreateDto movieCreateDto) {
        movieService.createMovie(movieCreateDto);
        return ResponseEntity.ok().build();
    }
}

