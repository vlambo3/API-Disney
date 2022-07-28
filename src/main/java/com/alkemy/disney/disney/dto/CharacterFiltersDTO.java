package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.repository.MovieRepository;
import lombok.Data;

import java.util.List;

@Data
public class CharacterFiltersDTO {
    private String name;
    private String age;
    private Double weight;
    private List<Long> movies;
    private String order;

    private MovieRepository movieRepository;

    public CharacterFiltersDTO(String name, String age, Double weight, List<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
        this.order = order;
    }

    public CharacterFiltersDTO() {
    }

    public boolean isASC() { return this.order.compareToIgnoreCase("ASC") == 0; }

    public boolean isDESC() { return this.order.compareToIgnoreCase("DESC") == 0; }

}
