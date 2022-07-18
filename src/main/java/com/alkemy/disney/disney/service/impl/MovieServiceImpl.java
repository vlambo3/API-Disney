package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.exceptions.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private MovieSpecification movieSpecification;

    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, false);
        return result;
    }

    public MovieDTO editMovie(Long id, MovieDTO dto) {
        Optional<MovieEntity> movie = movieRepository.findById(id);
        if(!movie.isPresent()) {
            throw new ParamNotFound( "The id movie didn`t exist");
        }
        movieMapper.movieEntityRefreshValues(movie.get(), dto);
        MovieEntity movieEdited = movieRepository.save(movie.get());
        MovieDTO result = movieMapper.movieEntity2DTO(movieEdited, false);
        return result;
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public void addCharacter(Long id, Long idCharacter) {
        MovieEntity movie = movieRepository.getById(id);
        movie.getCharacters().size();
        CharacterEntity characterEntity = characterService.getEntityById(idCharacter);
        movie.addCharacter(characterEntity);
        movieRepository.save(movie);
    }

    public void removeCharacter(Long id, Long idCharacter) {
        MovieEntity movie = movieRepository.getById(id);
        movie.getCharacters().size();
        CharacterEntity characterEntity = characterService.getEntityById(idCharacter);
        movie.removeCharacter(characterEntity);
        movieRepository.save(movie);
    }

    public MovieEntity getEntityById(Long id) {
        return movieRepository.getById(id);
    }

    public MovieDTO getDetailsById(Long id) {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound( "The id movie didn`t exist");
        }
        MovieDTO movieDTO = movieMapper.movieEntity2DTO(entity.get(), true);
        return movieDTO;
    }

    public List<MovieDTO> getByFilters(String name, Long idGenre, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, idGenre, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieDTO> dtos = this.movieMapper.movieEntityList2DTOList(entities, true);
        return dtos;
    }

}
