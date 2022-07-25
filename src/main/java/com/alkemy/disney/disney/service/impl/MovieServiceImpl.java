package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.controller.RestExceptionHandler;
import com.alkemy.disney.disney.dto.*;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.enumuration.ErrorEnum;
import com.alkemy.disney.disney.exceptions.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.disney.service.CharacterService;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private MovieSpecification movieSpecification;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private RestExceptionHandler restExceptionHandler;

    public MovieServiceImpl() {
    }

    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        return movieMapper.movieEntity2DTO(entitySaved, true);
    }

    public MovieDTO editMovie(Long id, MovieDTO dto) {
        Optional<MovieEntity> movie = movieRepository.findById(id);
        if(!movie.isPresent()) {
            throw new EntityNotFoundException( ErrorEnum.ID_MOVIE_NOT_FOUND.getMessage());
        }
        movieMapper.movieEntityRefreshValues(movie.get(), dto);
        MovieEntity movieEdited = movieRepository.save(movie.get());
        return movieMapper.movieEntity2DTO(movieEdited, true);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public void addCharacter(Long id, Long idCharacter) {
        /*Optional<MovieEntity> entity = movieRepository.findById(id);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException( ErrorEnum.ID_MOVIE_NOT_FOUND.getMessage());
        }*/
        MovieEntity movie = movieRepository.getById(id);
        CharacterEntity characterEntity = characterService.getEntityById(idCharacter);
        movie.addCharacter(characterEntity);
        movieRepository.save(movie);
    }

    public void removeCharacter(Long id, Long idCharacter) {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException( ErrorEnum.ID_MOVIE_NOT_FOUND.getMessage());
        }
        MovieEntity movie = movieRepository.getById(id);
        /*CharacterEntity characterFromDB = characterRepository.findById(id).orElseThrow(
                () -> new ParamNotFound(ErrorEnum.PARAMNOTFOUND.getMessage()));*/
        CharacterEntity characterEntity = characterService.getEntityById(idCharacter);
        movie.removeCharacter(characterEntity);
        movieRepository.save(movie);
    }


    /*
    public MovieEntity getEntityById(Long id) {
        return movieRepository.getById(id);
    }*/

    public MovieDTO getDetailsById(Long id) {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound( "The id movie didn`t exist");
        }
        return movieMapper.movieEntity2DTO(entity.get(), true);
    }

    public List<MovieBasicDTO> getByFilters(String name, Long idGenre, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, idGenre, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        return this.movieMapper.movieEntityList2DTOFiltersList(entities);
    }

}
