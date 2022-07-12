package com.alkemy.disney.disney.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;
    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy")
    private Date creationDate;
    private Double qualification;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movies_characters",
            joinColumns = @JoinColumn(name ="movie_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "character_id", nullable = false)
    )
    private Set<CharacterEntity> characters = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genre_id", insertable=false, updatable = false)
    private GenreEntity genre;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

}
