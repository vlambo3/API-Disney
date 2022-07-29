package com.alkemy.disney.disney.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;
    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM")
    private LocalDate creationDate;
    private Double qualification;

    //@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies_characters",
            joinColumns = @JoinColumn(name ="movie_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "character_id", nullable = false)
    )
    private Set<CharacterEntity> characters = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade =  CascadeType.PERSIST)
    @JoinColumn(name = "genre_id", insertable=false, updatable = false)
    private GenreEntity genre;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    private boolean deleted = Boolean.FALSE;

    public void addCharacter(CharacterEntity character) {
        characters.add(character);
    }

    public void removeCharacter(CharacterEntity character) {
        characters.remove(character);
    }
}
