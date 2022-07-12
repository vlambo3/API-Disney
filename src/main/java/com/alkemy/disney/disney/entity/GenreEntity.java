package com.alkemy.disney.disney.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@Data
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String image;

}
