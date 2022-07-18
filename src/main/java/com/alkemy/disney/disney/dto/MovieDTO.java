package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.GenreEntity;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String image;
    private String title;
    private Date creationDate;
    private Double qualification;
    private Set<CharacterDTO> characters;
    private GenreEntity genre;
    private Long genreId;
}
