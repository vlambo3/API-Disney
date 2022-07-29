package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreEntity genreDTO2Entity(GenreDTO dto) {
        GenreEntity entity = new GenreEntity();
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        return entity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity entity) {
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        return dto;
    }
}
