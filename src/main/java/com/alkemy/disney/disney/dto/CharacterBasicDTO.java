package com.alkemy.disney.disney.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterBasicDTO {
    private String image;
    private String name;
}
