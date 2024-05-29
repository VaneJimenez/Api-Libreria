package com.apireste2.backende2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apireste2.backende2.models.Comic;
import com.apireste2.backende2.repositories.ComicRepo;

@RestController
@RequestMapping("/api/comics")

public class ComicController {
    @Autowired
    private ComicRepo comicRepo;

    @GetMapping
    public List<Comic> obtenerTodosLosComics() {
        return comicRepo.findAll();
    }

    @GetMapping("/{id}")
    public Comic obtenerComicsId(@PathVariable Long id) {
        return comicRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Comic crearComic(@RequestBody Comic comic) {
        return comicRepo.save(comic);
    }

    @PutMapping("/{id}")
    public Comic actualizarComic(@PathVariable Long id, @RequestBody Comic comic) {
        comic.setId(id);
        return comicRepo.save(comic);
    }

    @DeleteMapping("/{id}")
    public void borrarComic(@PathVariable Long id) {
        comicRepo.deleteById(id);
    }
}
