package com.example.demo.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.domain.entities.Actor;
import com.example.demo.domain.entities.dtos.ActorShortProyection;

@RepositoryRestResource(exported = false)
public interface ActorRepository extends JpaRepository<Actor, Integer> {
	List<Actor> findByFirstNameStartingWithOrderByFirstNameAsc(String prefijo);
	List<ActorShortProyection> findByActorIdIsNotNull();
}
