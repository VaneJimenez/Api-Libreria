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

import com.apireste2.backende2.models.Libro;
import com.apireste2.backende2.repositories.LibroRepo;

@RestController
@RequestMapping("/api/libros")

public class LibroController {

    @Autowired
    private LibroRepo libroRepo;

    @GetMapping
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepo.findAll();
    }

    @GetMapping("/{id}")
    public Libro obtenerLibrosId(@PathVariable Long id) {
        return libroRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroRepo.save(libro);
    }

    @PutMapping("/{id}")
    public Libro actualizarLibro(@PathVariable("id") Long id, @RequestBody Libro libro) {
        libro.setId(id);
        return libroRepo.save(libro);
    }
    
    @DeleteMapping("/{id}")
    public void borrarLibro(@PathVariable Long id) {
        libroRepo.deleteById(id);
    }

}
