package com.apireste2.backende2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apireste2.backende2.models.Libro;

public interface LibroRepo extends JpaRepository<Libro, Long>{

}
