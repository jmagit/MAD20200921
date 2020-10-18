package com.example.demo.domain.entities.dtos;

import org.springframework.beans.factory.annotation.Value;

public interface ActorShortProyection {
	String getFirstName();
	String getLastName();
	@Value("#{target.lastName + ', ' + target.firstName}")
	String getNombreCompleto();
}
