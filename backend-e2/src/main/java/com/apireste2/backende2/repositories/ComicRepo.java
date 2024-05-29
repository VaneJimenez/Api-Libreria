package com.apireste2.backende2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apireste2.backende2.models.Comic;

public interface ComicRepo extends JpaRepository<Comic, Long>{

}
