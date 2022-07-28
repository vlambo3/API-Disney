package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity entity = new MovieEntity();
        convertBasicValues(entity,dto);
        return  entity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setQualification(entity.getQualification());
        if(loadCharacters) {
            Set<CharacterDTO> charactersDTO = this.characterMapper.characterEntitySet2DTOSet(entity.getCharacters(),true);
            dto.setCharacters(charactersDTO);
        }
        dto.setGenre(entity.getGenre());
        return dto;
    }

    public MovieBasicDTO movieEntity2DTOFilters(MovieEntity entity) {
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public void movieEntityRefreshValues(MovieEntity entity, MovieDTO dto) {
        convertBasicValues(entity,dto);
    }

    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity: entities) {
            dtos.add(this.movieEntity2DTO(entity, loadCharacters));
        }
        return dtos;
    }

    public List<MovieBasicDTO> movieEntityList2DTOFiltersList(List<MovieEntity> entities) {
        List<MovieBasicDTO> dtos = new ArrayList<>();
        for (MovieEntity entity: entities) {
            dtos.add(this.movieEntity2DTOFilters(entity));
        }
        return dtos;
    }

    public void convertBasicValues(MovieEntity entity, MovieDTO dto) {
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(
                this.string2LocalDate(dto.getCreationDate().toString()));
        entity.setQualification(dto.getQualification());
        entity.setGenreId(dto.getGenreId());
    }

}
