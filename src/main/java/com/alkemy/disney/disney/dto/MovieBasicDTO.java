package com.alkemy.disney.disney.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MovieBasicDTO {
    private String image;
    private String title;
    private Date creationDate;

}
