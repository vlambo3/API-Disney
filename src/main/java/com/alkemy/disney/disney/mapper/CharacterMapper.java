package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private  MovieMapper movieMapper;

    public CharacterEntity characterDTO2CharacterEntity(CharacterDTO dto) {
        CharacterEntity entity = new CharacterEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        return  entity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        if(loadMovies) {
            List<MovieDTO> moviesDTO = this.movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
            dto.setMovies(moviesDTO);
        }
        return dto;
    }

    public Set<CharacterEntity> characterDTOList2Entity(List<CharacterDTO> dtos) {
        Set<CharacterEntity> entities = new HashSet<>();
        for (CharacterDTO dto: dtos)  {
            entities.add(characterDTO2CharacterEntity(dto));
        }
        return entities;
    }

    public List<CharacterDTO> characterEntitySet2DTOList(Collection<CharacterEntity> entities, boolean loadMovies)  {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity: entities)  {
            dtos.add(this.characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }

    public Set<CharacterDTO> characterEntitySet2DTOSet(Collection<CharacterEntity> entities, boolean loadMovies)  {
        Set<CharacterDTO> dtos = new HashSet<>();
        for (CharacterEntity entity: entities)  {
            dtos.add(this.characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }

    public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO dto) {
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
    }

}
