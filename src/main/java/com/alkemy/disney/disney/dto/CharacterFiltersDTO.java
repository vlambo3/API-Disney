package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.repository.MovieRepository;
import lombok.Data;

import java.util.List;

@Data
public class CharacterFiltersDTO {
    private String name;
    private String age;
    private List<Long> movies;

    public CharacterFiltersDTO(String name, String age, List<Long> movies) {
        this.name = name;
        this.age = age;
        this.movies = movies;
    }
}
