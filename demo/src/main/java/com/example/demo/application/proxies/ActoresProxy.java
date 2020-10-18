package com.example.demo.application.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.domain.entities.dtos.ActorShortDTO;

@FeignClient(name = "actores", url = "http://localhost:8001")
public interface ActoresProxy {
	@GetMapping("/actores")
	Page<ActorShortDTO> getAll();
	@GetMapping("/actores/{id}")
	ActorShortDTO get(@PathVariable int id);
	@PostMapping(path = "/actores", consumes = "application/json")
	void add(@RequestBody ActorShortDTO item);
	@PutMapping(path = "/actores/{id}", consumes = "application/json")
	void modify(@PathVariable int id, @RequestBody ActorShortDTO item);
	@DeleteMapping(path = "/actores/{id}")
	void delete(@PathVariable int id);	
}
