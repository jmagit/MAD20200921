package com.example.demo.domain.entities.dtos;

import java.sql.Timestamp;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.domain.entities.Film;

@Projection(name = "FilmShot", types = { Film.class }) 
public interface FilmShotProjection {
	int getFilmId();
	String getTitle();
	Timestamp getLastUpdate();
}
