package com.apireste2.backende2.controllers;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.apireste2.backende2.models.Comic;
import com.apireste2.backende2.repositories.ComicRepo;

@WebMvcTest(ComicController.class)
public class ComicControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private ComicRepo comicRepo;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void TestObtenerComics() throws Exception {
        when(comicRepo.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/comics"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));

        verify(comicRepo, times(1)).findAll();
    }

    @Test
    public void TestObtenerComicsId() throws Exception {
        Comic comic = new Comic();
        comic.setId(1L);
        when(comicRepo.findById(1L)).thenReturn(Optional.of(comic));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/comics/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        verify(comicRepo, times(1)).findById(1L);
    }

    @Test
    public void TestCrearComic() throws Exception {
        Comic comic = new Comic();
        comic.setId(1L);

        when(comicRepo.save(any(Comic.class))).thenReturn(comic);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/comics")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": null, \"nombre\": null, \"autor\": null, \"artista\": null}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        verify(comicRepo, times(1)).save(any(Comic.class));
    }

    @Test
    public void TestActualizarComic() throws Exception {
        Comic comic = new Comic();
        comic.setId(1L);

        when(comicRepo.findById(1L)).thenReturn(Optional.of(comic));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/comics")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": null,\"nombre\": null, \"autor\": null, \"artista\": null}"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        verify(comicRepo, times(1)).findById(1L);
    }

    @Test
    public void TestBorrarComic() throws Exception {
        doNothing().when(comicRepo).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/comics/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(comicRepo, times(1)).deleteById(1L);
    }

}
