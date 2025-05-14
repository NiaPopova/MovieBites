package com.web.java.project.movie.bites.entities.recepies;

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
    private String dificulty;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "CONTAINS",
        joinColumns = @JoinColumn(name = "RECIPE_ID"),
        inverseJoinColumns = @JoinColumn(name = "INGREDIENT_ID"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"RECIPE_ID", "INGREDIENT_ID"}))
    private Set<Ingredient> ingredients = new HashSet<>();




}