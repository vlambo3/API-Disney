package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
        return entity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setQualification(entity.getQualification());
        if(loadCharacters) {
            Set<CharacterDTO> charactersDTO = this.characterMapper.characterEntitySet2DTOSet(entity.getCharacters(),false);
            dto.setCharacters(charactersDTO);
        }
        dto.setGenre(entity.getGenre());
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

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2DTO(entity, true));
        }
        return dtos;
    }

    public List<MovieBasicDTO> movieEntityList2DTOBasicList(List<MovieEntity> entities) {
        List<MovieBasicDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2DTOBasic(entity));
        }
        return dtos;
    }

    public MovieBasicDTO movieEntity2DTOBasic(MovieEntity entity) {
        MovieBasicDTO dtoBasic = new MovieBasicDTO();
        dtoBasic.setImage(entity.getImage());
        dtoBasic.setTitle(entity.getTitle());
        dtoBasic.setCreationDate(entity.getCreationDate());
        return dtoBasic;
    }

    public void convertBasicValues(MovieEntity entity, MovieDTO dto) {
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(
                this.string2LocalDate(dto.getCreationDate().toString()));
        entity.setQualification(dto.getQualification());
        entity.setGenreId(dto.getGenreId());
        entity.setCharacters(characterMapper.characterDTOList2EntityList(dto.getCharacters()));
    }

}
