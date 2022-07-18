package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;

public interface CharacterService {
    CharacterDTO save(CharacterDTO dto);
    CharacterDTO editCharacter(Long id, CharacterDTO dto);
    void delete(Long id);
    CharacterEntity getEntityById(Long id);
    void addMovie(Long id, Long idMovie);
}
