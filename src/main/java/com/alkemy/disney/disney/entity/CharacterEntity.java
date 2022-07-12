package com.alkemy.disney.disney.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Data
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;
    private String name;
    private int age;
    private Double weight;
    private String history;

    @ManyToMany(mappedBy="characters", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<MovieEntity> movies = new ArrayList<>();

    public void addMovie(MovieEntity movie) {
        this.movies.add(movie);
    }

    public void removeMovie(MovieEntity movie) {
        this.movies.remove(movie);
    }

}
