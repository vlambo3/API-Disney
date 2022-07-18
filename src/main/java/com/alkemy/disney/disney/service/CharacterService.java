package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface CharacterService {
    CharacterDTO save(CharacterDTO dto);
    CharacterDTO editCharacter(Long id, CharacterDTO dto);
    void delete(Long id);
    CharacterEntity getEntityById(Long id);
    void addMovie(Long id, Long idMovie);
    void removeMovie(Long id, Long idMovie);

    CharacterDTO getDetailsById(Long id);
    List<CharacterDTO> getByFilters(String name, String age, Set<Long> movies, String order);
}
