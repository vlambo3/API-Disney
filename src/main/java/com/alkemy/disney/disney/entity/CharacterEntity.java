package com.alkemy.disney.disney.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Data
@SQLDelete(sql = "UPDATE character SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;
    private String name;
    private String age;
    private Double weight;
    private String history;

    //@ManyToMany(mappedBy="characters", fetch = FetchType.LAZY, cascade =  {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToMany(mappedBy="characters", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MovieEntity> movies = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;
    public void addMovie(MovieEntity movie) {
        this.movies.add(movie);
        //movie.getCharacters().add(this);
    }

    public void removeMovie(MovieEntity movie) {
        this.movies.remove(movie);
        //movie.getCharacters().remove(this);
    }

}
