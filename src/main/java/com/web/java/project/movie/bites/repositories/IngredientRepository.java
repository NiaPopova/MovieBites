package com.web.java.project.movie.bites.repositories;


import com.web.java.project.movie.bites.entities.recepies.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    // Проверка дали има ингредиент с дадено име
    Optional<Ingredient> findByName(String name);

    // Търсене на всички ингредиенти, съдържащи текст
    List<Ingredient> findByNameContainingIgnoreCase(String keyword);
}

