package com.web.java.project.movie.bites.entities;

import com.web.java.project.movie.bites.entities.recepies.Recipe;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "MOVIES")
public class Movie {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "PHOTO")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Recipe> recipes = new HashSet<>();
}
