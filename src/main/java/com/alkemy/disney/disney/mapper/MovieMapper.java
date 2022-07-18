package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterMapper characterMapper;

    public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity entity = new MovieEntity();
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        entity.setQualification(dto.getQualification());
        entity.setGenre(dto.getGenre());
        return  entity;
    }


    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
        dto.setQualification(entity.getQualification());
        //dto.setCharacters(entity.getCharacters());
        if(loadCharacters) {
            Set<CharacterDTO> charactersDTO = this.characterMapper.characterEntitySet2DTOSet(entity.getCharacters(),false);
            dto.setCharacters(charactersDTO);
        }
        dto.setGenre(entity.getGenre());
        return dto;
    }

    public void movieEntityRefreshValues(MovieEntity entity, MovieDTO dto) {
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        entity.setQualification(dto.getQualification());
        entity.setGenre(dto.getGenre());
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity: entities) {
            dtos.add(this.movieEntity2DTO(entity, loadCharacters));
        }
        return dtos;
    }


}
