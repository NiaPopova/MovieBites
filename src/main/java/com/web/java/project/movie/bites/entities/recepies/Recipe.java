package com.web.java.project.movie.bites.entities.recepies;

import com.web.java.project.movie.bites.entities.Movie;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "Recipes")
@Getter
@Setter
public class Recipe {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "DIFICULTY")
    private String difficulty;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "contains",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"recipe_id", "ingredient_id"}))
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;



}