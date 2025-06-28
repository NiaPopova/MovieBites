package com.web.java.project.movie.bites.repositories;


import com.web.java.project.movie.bites.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Търсене по жанр (genreId)
    List<Movie> findByGenreId(Integer genre_id);

    // Търсене по година на издаване
    List<Movie> findByYear(int year);

    // Търсене по режисьор
    List<Movie> findByDirectorIgnoreCase(String director);
}

