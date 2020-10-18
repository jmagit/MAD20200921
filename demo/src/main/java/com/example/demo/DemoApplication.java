package com.example.demo;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.entities.Actor;
import com.example.demo.domain.entities.FilmActor;
import com.example.demo.domain.entities.dtos.ActorShortDTO;
import com.example.demo.domain.entities.dtos.ActorShortProyection;
import com.example.demo.domain.services.ActorService;
import com.example.demo.infraestructure.repositories.ActorRepository;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@EnableFeignClients("com.example.demo.application.proxies")
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private ActorRepository repositorio;

	@Autowired
	private ActorService srv;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		System.out.println("Hola mundo");

//	 	for(Actor item: repositorio.findAll())
//	 		System.out.println(item);
//	 	Optional<Actor> rslt = repositorio.findById(1);
//		if(rslt.isPresent()) {
//			Actor item = rslt.get();
//			item.setFirstName(item.getFirstName().toUpperCase());
//			repositorio.save(item);
//		}
//		rslt = repositorio.findById(1);
//		if(rslt.isPresent()) {
//			System.out.println(rslt.get());
//		}
//	 	for(Actor item: repositorio.findByFirstNameStartingWithOrderByFirstNameAsc("A"))
//	 		System.out.println(item);
//	 	Optional<Actor> rslt = repositorio.findById(1);
//		if(rslt.isPresent()) {
//		 	for(FilmActor item: rslt.get().getFilmActors())
//		 		System.out.println(item.getFilm().getTitle());
//		}
//	 	for(ActorShortDTO item: repositorio.findAll().stream()
//	 			.map(o -> ActorShortDTO.from(o))
//	 			.collect(Collectors.toList()))
// 		System.out.println(item.getFirstName() + " " + item.getLastName());
//	 	for(ActorShortProyection item: repositorio.findByActorIdIsNotNull())
//	 		System.out.println(item.getNombreCompleto()); //.getFirstName() + " " + item.getLastName());
//		Optional<Actor> rslt = srv.get(1);
//		if (rslt.isPresent()) {
//			Actor item = rslt.get();
//			item.setFirstName(" ");
//			try {
//				srv.modify(item);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				for(ConstraintViolation<Actor> err: srv.getError(item))
//					System.out.println(err.getMessage());
//			}
//		}
	}

	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.OAS_30)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.example.demo.application.resources"))
	      .paths(PathSelectors.ant("/**"))
	      .build()
	      .apiInfo(new ApiInfoBuilder()
	                .title("Ejemplos del curso")
	                .version("1.0")
	                .license("Apache License Version 2.0")
	                .contact(new Contact("Yo Mismo", "http://www.example.com", "myeaddress@example.com"))
	                .build());
	}

	@Bean 
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
