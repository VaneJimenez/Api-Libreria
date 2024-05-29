package com.apireste2.backende2.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

import com.apireste2.backende2.models.Libro;
import com.apireste2.backende2.repositories.LibroRepo;


@WebMvcTest(LibroController.class)
public class LibroControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private LibroRepo libroRepo;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void TestObtenerTodosLibros() throws Exception {
        when(libroRepo.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/libros"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[]"));

        verify(libroRepo, times(1)).findAll();
    }

    @Test
    public void TestObtenerLibroId() throws Exception {
        Libro libro = new Libro();
        libro.setId(1L);
        when(libroRepo.findById(1L)).thenReturn(Optional.of(libro));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/libros/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        verify(libroRepo, times(1)).findById(1L);
    }
    
    @Test
    public void TestCrearLibro() throws Exception{
        Libro libro = new Libro();
        libro.setId(1L);
        
        when(libroRepo.save(any(Libro.class))).thenReturn(libro);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/libros")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\": null,\"nombre\": null, \"autor\": null, \"genero\": null}"))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        verify(libroRepo, times(1)).save(any(Libro.class));
    }
    
    @Test
    public void TestActualizarLibro() throws Exception{
        Libro libro = new Libro();
        libro.setId(1L);
        
        when(libroRepo.findById(1L)).thenReturn(Optional.of(libro));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/libros")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\": null,\"nombre\": null, \"autor\": null, \"genero\": null}"))

        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        verify(libroRepo, times(1)).findById(1L);
    }
    
    @Test
    public void TestBorrarLibro() throws Exception{
        doNothing().when(libroRepo).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/libros/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
        verify(libroRepo, times(1)).deleteById(1L);
    }
    
}
