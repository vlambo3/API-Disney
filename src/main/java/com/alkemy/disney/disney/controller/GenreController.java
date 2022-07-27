package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    //endpoint para guardar/crear generos
    @PostMapping
    public ResponseEntity<GenreDTO>save(@RequestBody GenreDTO genre) {
        GenreDTO genreSave = genreService.save(genre);
        return  ResponseEntity.status(HttpStatus.CREATED).body(genreSave);
    }
}
