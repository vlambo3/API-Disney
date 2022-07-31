package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.controller.RestExceptionHandler;
import com.alkemy.disney.disney.dto.*;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.enumuration.ErrorEnum;
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

    public MovieDTO save(MovieDTO dto) {
        return movieMapper.movieEntity2DTO(movieRepository.save(movieMapper.movieDTO2Entity(dto)), true);
    }

    public MovieDTO editMovie(Long id, MovieDTO dto) {
        MovieEntity movieFromDB = this.movieRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException( ErrorEnum.ID_MOVIE_NOT_FOUND.getMessage()));
        movieMapper.movieEntityRefreshValues(movieFromDB, dto);
        return movieMapper.movieEntity2DTO(movieRepository.save(movieFromDB), false);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> movies = movieRepository.findAll();
        return movieMapper.movieEntityList2DTOList(movies);
    }

    public void addCharacter(Long id, Long idCharacter) {
        MovieEntity movieFromDB = movieRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException( ErrorEnum.ID_MOVIE_NOT_FOUND.getMessage()));
        CharacterEntity characterFromDB = characterRepository.findById(idCharacter).orElseThrow(
                ()-> new EntityNotFoundException( ErrorEnum.ID_CHARACTER_NOT_FOUND.getMessage()));
        movieFromDB.addCharacter(characterService.getEntityById(idCharacter));
        movieRepository.save(movieFromDB);
    }

    public void removeCharacter(Long id, Long idCharacter) {
        MovieEntity movieFromDB = movieRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException( ErrorEnum.ID_MOVIE_NOT_FOUND.getMessage()));
        CharacterEntity characterFromDB = characterRepository.findById(idCharacter).orElseThrow(
                ()-> new EntityNotFoundException( ErrorEnum.ID_CHARACTER_NOT_FOUND.getMessage()));
        movieFromDB.removeCharacter(characterService.getEntityById(idCharacter));
        movieRepository.save(movieFromDB);
    }

    public MovieDTO getDetailsById(Long id) {
        MovieEntity movieFromDB = this.movieRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException( ErrorEnum.ID_MOVIE_NOT_FOUND.getMessage()));
        return movieMapper.movieEntity2DTO(movieFromDB, true);
    }

    public List<MovieBasicDTO> getByFilters(String title, Long idGenre, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title, idGenre, order);
        if(movieMapper.movieEntityList2DTOBasicList(movieRepository.findAll(movieSpecification.getByFilters(filtersDTO))).isEmpty()) {
            return movieMapper.movieEntityList2DTOBasicList(movieRepository.findAll());
        } else {
            return movieMapper.movieEntityList2DTOBasicList(movieRepository.findAll(movieSpecification.getByFilters(filtersDTO)));
        }
    }

}
