package com.example.demo.domain.entities.dtos;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.example.demo.domain.entities.Actor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Edicion de Actor", description = "Version para editar los actores")
@Data @AllArgsConstructor @NoArgsConstructor
public class ActorEditDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int actorId;
	@ApiModelProperty(value = "nombre", notes = "Nombre del actor, que debe ser ...", required = true)
	private String firstName;
	@Size(max=45)
	@ApiModelProperty(value = "apellidos", notes = "Apellidos del actor, que debe ser ...", required = true)
	private String lastName;

	public static ActorEditDTO from(Actor source) {
		return new ActorEditDTO(
					source.getActorId(),
					source.getFirstName(),
					source.getLastName()
				);
	}
	public static Actor from(ActorEditDTO source) {
		return new Actor(
					source.getActorId(),
					source.getFirstName(),
					source.getLastName()
				);
	}
	public void merge(Actor target) {
		target.setActorId(getActorId());
		target.setFirstName(getFirstName());
		target.setLastName(getLastName());
	}
}
