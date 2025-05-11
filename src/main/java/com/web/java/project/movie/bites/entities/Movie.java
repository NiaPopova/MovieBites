package com.web.java.project.movie.bites.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

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
}
