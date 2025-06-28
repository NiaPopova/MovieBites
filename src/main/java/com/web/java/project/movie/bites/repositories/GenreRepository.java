package com.web.java.project.movie.bites.repositories;

import com.web.java.project.movie.bites.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    // Търси жанр по име (case insensitive)
    Optional<Genre> findByNameIgnoreCase(String name);

    // Проверява дали жанр с дадено име съществува
    boolean existsByNameIgnoreCase(String name);

}

