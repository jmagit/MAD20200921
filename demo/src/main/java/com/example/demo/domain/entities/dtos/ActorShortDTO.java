package com.example.demo.domain.entities.dtos;

import java.io.Serializable;

import com.example.demo.domain.entities.Actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActorShortDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int actorId;
	private String firstName;
	private String lastName;

	public static ActorShortDTO from(Actor source) {
		return new ActorShortDTO(
					source.getActorId(),
					source.getFirstName(),
					source.getLastName()
				);
	}
	public static Actor from(ActorShortDTO source) {
		return new Actor(
					source.getActorId(),
					source.getFirstName(),
					source.getLastName()
				);
	}
}
