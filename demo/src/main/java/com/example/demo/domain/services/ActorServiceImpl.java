package com.example.demo.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.Actor;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.infraestructure.repositories.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {
	@Autowired
	ActorRepository dao;
	@Autowired
	Validator validator;
	
	public Set<ConstraintViolation<Actor>> getError(Actor item) {
		Set<ConstraintViolation<Actor>> rstl = validator.validate(item);
		// Validaciones personalizadas
		return rstl;
	}

	public boolean isValid(Actor item) {
		return getError(item).size() == 0;
	}
	public boolean isInvalid(Actor item) {
		return !isValid(item);
	}
	@Override
	public List<Actor> getAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Actor> get(int id) {
		return dao.findById(id);
	}
	
	@Override
	@Transactional
	public Actor add(Actor item) throws InvalidDataException {
		if(isInvalid(item))
			throw new InvalidDataException("Invalid data");
		if(get(item.getActorId()).isPresent())
			throw new InvalidDataException("Duplicate key");
		return dao.save(item);
	}
	@Override
	public void modify(Actor item) throws NotFoundException, InvalidDataException {
		if(isInvalid(item))
			throw new InvalidDataException("Invalid data");
		if(!get(item.getActorId()).isPresent())
			throw new NotFoundException();
		dao.save(item);
	}
	@Override
	public void delete(int id) throws NotFoundException {
		if(!get(id).isPresent())
			throw new NotFoundException();
		dao.deleteById(id);
	}
	@Override
	public void delete(Actor item) throws NotFoundException {
		delete(item.getActorId());
	}
}
