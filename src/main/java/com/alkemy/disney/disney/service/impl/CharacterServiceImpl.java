package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.enumuration.ErrorEnum;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.service.CharacterService;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private MovieService movieService;

    private CharacterSpecification characterSpecification;


    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entitySaved = characterRepository.save(characterMapper.characterDTO2CharacterEntity(dto));
        return characterMapper.characterEntity2DTO(entitySaved, true);
    }

    @Override
    public CharacterDTO editCharacter(Long id, CharacterDTO dto) {
        CharacterEntity characterFromDB = this.characterRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException( ErrorEnum.ID_CHARACTER_NOT_FOUND.getMessage()));
        characterMapper.characterEntityRefreshValues(characterFromDB, dto);
        return characterMapper.characterEntity2DTO(characterRepository.save(characterFromDB), true);
    }

    @Override
    public void delete(Long id) {
        characterRepository.deleteById(id);
    }

    public List<CharacterDTO> getAllCharacters() {
        return characterMapper.characterEntityList2DTOList(characterRepository.findAll());
    }

    public CharacterEntity getEntityById(Long id) {
        return characterRepository.getById(id);
    }

    public CharacterDTO getDetailsById(Long id) {
        CharacterEntity characterFromDB = this.characterRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException( ErrorEnum.ID_CHARACTER_NOT_FOUND.getMessage()));
        return characterMapper.characterEntity2DTO(characterFromDB, true);
    }

    public List<CharacterBasicDTO> getByFilters(String name, String age, Double weight, List<Long> movies, String order) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies, order);
        return this.characterMapper.characterEntitySet2DTOFiltersList(characterRepository.findAll(this.characterSpecification.getByFilters(filtersDTO)));
    }

}
