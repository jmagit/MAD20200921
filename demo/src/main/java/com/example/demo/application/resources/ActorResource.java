package com.example.demo.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.entities.Actor;
import com.example.demo.domain.entities.dtos.ActorEditDTO;
import com.example.demo.domain.entities.dtos.ActorShortDTO;
import com.example.demo.domain.entities.dtos.FilmShotDTO;
import com.example.demo.domain.services.ActorService;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;

@Api(tags = {"Actores-Service"})
@Tag(name = "Actores-Service", description = "Mantenimiento de los actores de las peliculas")
@RestController
@RequestMapping("/api/actores")
public class ActorResource {
	@Autowired
	private ActorService srv;

	@GetMapping
	public List<ActorShortDTO> getAll() {
		return srv.getAll().stream().map(o -> ActorShortDTO.from(o))
			.collect(Collectors.toList());
	}

	@ApiOperation(value = "Localizar un actor", notes = "Devuelve un actor por su identificador" )
	@ApiResponses({
		@ApiResponse(code = 200, message = "Actor encontrado"),
		@ApiResponse(code = 404, message = "Actor no encontrado")
	})
	@GetMapping(path = "/{id}")
	public ActorEditDTO getOne(@ApiParam(value = "id", required=true,name = "Identificador") @PathVariable int id) throws Exception {
		Optional<Actor> rslt = srv.get(id);
		if(rslt.isPresent())
			return ActorEditDTO.from(rslt.get());
		throw new NotFoundException();
	}
	@GetMapping(path = "/{id}/peliculas")
	public List<FilmShotDTO> getPeliculas(@PathVariable int id) throws Exception {
		Optional<Actor> rslt = srv.get(id);
		if(!rslt.isPresent())
			throw new NotFoundException();
		return rslt.get().getFilmActors().stream()
				.map(o -> new FilmShotDTO(o.getFilm().getFilmId(), o.getFilm().getTitle()))
				.collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ActorEditDTO item) throws Exception {
		Actor newItem = ActorEditDTO.from(item);
		newItem = srv.add(newItem);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable int id, @Valid @RequestBody ActorEditDTO item) throws Exception {
		if(id != item.getActorId())
			throw new BadRequestException("No coinciden los identificadores");
		Optional<Actor> rslt = srv.get(id);
		if(!rslt.isPresent())
			throw new NotFoundException();
		Actor oldItem = rslt.get();
		item.merge(oldItem);
		srv.modify(oldItem);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws Exception {
		srv.delete(id);
	}
}
