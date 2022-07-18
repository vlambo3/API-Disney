package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
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

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id) {
        CharacterDTO character = characterService.getDetailsById(id);
        return ResponseEntity.ok(character);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) Set<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order) {
        List<CharacterDTO> characters = this.characterService.getByFilters(name, age, movies, order);
        return ResponseEntity.ok(characters);
    }
}
