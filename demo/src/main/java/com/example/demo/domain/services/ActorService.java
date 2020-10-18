package com.example.demo.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.example.demo.domain.entities.Actor;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.exception.NotFoundException;

public interface ActorService {

	List<Actor> getAll();

	Optional<Actor> get(int id);

	Actor add(Actor item) throws InvalidDataException;

	void modify(Actor item) throws NotFoundException, InvalidDataException;

	void delete(int id) throws NotFoundException;

	void delete(Actor item) throws NotFoundException;
	
	Set<ConstraintViolation<Actor>> getError(Actor item);
	
	boolean isValid(Actor item);
	
	boolean isInvalid(Actor item);

}