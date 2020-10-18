package com.example.demo.domain.entities.dtos;

import java.io.Serializable;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.domain.entities.Film;

public class FilmShotDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int filmId;
	private String title;
	
	
	public FilmShotDTO() {
		
	}
	public FilmShotDTO(int filmId, String title) {
		super();
		this.filmId = filmId;
		this.title = title;
	}
	
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
