package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
        MovieDTO movieSaved = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> editMovie(@PathVariable Long id, @RequestBody MovieDTO dto) {
        MovieDTO result = movieService.editMovie(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/character/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        movieService.addCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}/character/{idCharacter}")
    public ResponseEntity<Void> removeCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        movieService.removeCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable Long id) {
        MovieDTO movie = movieService.getDetailsById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long idGenre,
            @RequestParam(required = false, defaultValue = "ASC") String order) {
        List<MovieDTO> movies = this.movieService.getByFilters(name, idGenre, order);
        return ResponseEntity.ok(movies);
    }

}
