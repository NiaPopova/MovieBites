package com.web.java.project.movie.bites.entities.recepies;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.UniqueConstraint;
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