package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.GenreEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String image;
    @NotNull
    private String title;
    private String creationDate;
    @Min(1)
    @Max(5)
    private Double qualification;
    private Set<CharacterDTO> characters;
    private GenreEntity genre;
    private Long genreId;
}
