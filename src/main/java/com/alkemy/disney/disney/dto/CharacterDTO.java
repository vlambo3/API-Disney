package com.alkemy.disney.disney.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
public class CharacterDTO {
    private Long id;
    private String image;
    @NotNull
    private String name;
    @Pattern(regexp = "\\d{2}", message = "The format should be aa")
    private String age;
    private Double weight;
    private String history;
    private List<MovieDTO> movies;

}
