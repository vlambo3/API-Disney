package com.alkemy.disney.disney.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CharacterFiltersDTO {
    private String name;
    private String age;
    private Set<Long> movies;
    private String order;

    public CharacterFiltersDTO(String name, String age, Set<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.movies = movies;
        this.order = order;
    }

    public boolean isASC() { return this.order.compareToIgnoreCase("ASC") == 0; }

    public boolean isDESC() { return this.order.compareToIgnoreCase("DESC") == 0; }
}
