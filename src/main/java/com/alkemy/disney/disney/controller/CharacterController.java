package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@Valid @RequestBody CharacterDTO character) {
        CharacterDTO characterSaved = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> editCharacter(@PathVariable Long id, @RequestBody CharacterDTO dto) {
        CharacterDTO result = characterService.editCharacter(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id) {
        CharacterDTO character = characterService.getDetailsById(id);
        return ResponseEntity.ok(character);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CharacterBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) List<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order) {
        List<CharacterBasicDTO> characters = characterService.getByFilters(name, age, weight, movies, order);
        return ResponseEntity.ok(characters);
    }
}
