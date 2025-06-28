package com.web.java.project.movie.bites.repositories;

import com.web.java.project.movie.bites.entities.recepies.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // Връща всички рецепти по даден movieId
    List<Recipe> findByMovieId(Long movieId);

    // Връща рецепти с определена трудност
    List<Recipe> findByDifficulty(String difficulty);

    // Търсене по част от името (пример)
    List<Recipe> findByNameContainingIgnoreCase(String keyword);
}
