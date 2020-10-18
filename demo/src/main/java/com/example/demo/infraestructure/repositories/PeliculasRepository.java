package com.example.demo.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.demo.domain.entities.Film;

@RepositoryRestResource(path = "peliculas", itemResourceRel="pelicula", collectionResourceRel="peliculas")
public interface PeliculasRepository extends JpaRepository<Film, Integer> {
	@RestResource(exported = false)
	void deleteById(Integer id);

}
