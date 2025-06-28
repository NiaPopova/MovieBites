package com.web.java.project.movie.bites.services;

import com.web.java.project.movie.bites.dto.MovieCreateDto;
import com.web.java.project.movie.bites.dto.MovieDto;
import com.web.java.project.movie.bites.entities.Genre;
import com.web.java.project.movie.bites.entities.Movie;
import com.web.java.project.movie.bites.repositories.GenreRepository;
import com.web.java.project.movie.bites.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        return mapToDto(movie);
    }

    @Override
    public void createMovie(MovieCreateDto dto) {
        Genre genre = genreRepository.findById(dto.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + dto.getGenreId()));

        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDirector(dto.getDirector());
        movie.setYear(dto.getYear());
        movie.setSummary(dto.getSummary());
        movie.setPhoto(dto.getPhoto());
        movie.setGenre(genre);

        movieRepository.save(movie);
    }

    private MovieDto mapToDto(Movie movie) {
        return new MovieDto(
                movie.getId().longValue(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getYear(),
                movie.getSummary(),
                movie.getPhoto(),
                movie.getGenre() != null ? movie.getGenre().getName() : null
        );
    }
}

