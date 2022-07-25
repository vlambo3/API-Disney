package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    MovieDTO save(MovieDTO dto);
    MovieDTO editMovie(Long id, MovieDTO dto);
    void delete(Long id);
    void addCharacter(Long id, Long idCharacter);
    void removeCharacter(Long id, Long idCharacter);
    /*
    MovieEntity getEntityById(Long id);*/
    MovieDTO getDetailsById(Long id);

    List<MovieBasicDTO> getByFilters(String name, Long idGenre, String order);
}
