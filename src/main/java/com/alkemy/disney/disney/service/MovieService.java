package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.MovieEntity;

public interface MovieService {
    MovieDTO save(MovieDTO dto);
    MovieDTO editMovie(Long id, MovieDTO dto);
    void delete(Long id);
    void addCharacter(Long id, Long idCharacter);

    MovieEntity getEntityById(Long id);
}
