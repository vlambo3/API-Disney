package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.exceptions.ParamNotFound;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.service.CharacterService;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieService movieService;


    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2CharacterEntity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    @Override
    public CharacterDTO editCharacter(Long id, CharacterDTO dto) {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if(!character.isPresent()) {
            throw new ParamNotFound( "The id character didn`t exist");
        }
        characterMapper.characterEntityRefreshValues(character.get(), dto);
        CharacterEntity characterEdited = characterRepository.save(character.get());
        CharacterDTO result = characterMapper.characterEntity2DTO(characterEdited, false);
        return result;
    }

    @Override
    public void delete(Long id) {
        characterRepository.deleteById(id);
    }

    public CharacterEntity getEntityById(Long id) {
        return characterRepository.getById(id);
    }

    public void addMovie(Long id, Long idMovie) {
        CharacterEntity character = characterRepository.getById(id);
        character.getMovies().size();
        MovieEntity movieEntity = movieService.getEntityById(idMovie);
        character.addMovie(movieEntity);
        characterRepository.save(character);
    }
}
