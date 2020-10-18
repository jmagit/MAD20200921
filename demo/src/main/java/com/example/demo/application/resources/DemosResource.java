package com.example.demo.application.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.application.proxies.ActoresProxy;
import com.example.demo.domain.entities.dtos.ActorShortDTO;
import com.example.demo.domain.entities.dtos.AuthDTO;
import com.example.demo.domain.entities.dtos.MenuItem;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;

@RestController
@RequestMapping(path = "/api/demos")
public class DemosResource {
	
	@GetMapping("/params/{id}")
	public String cotilla(
	        @PathVariable String id,
	        @RequestParam(required = false, defaultValue = "sin nombre") String nom,
	        @RequestHeader("Accept-Language") String language) { 
	    StringBuilder sb = new StringBuilder();
	    sb.append("id: " + id + "\n");
	    sb.append("nom: " + nom + "\n");
	    sb.append("language: " + language + "\n");
	    return sb.toString();
	}
	@GetMapping(path = "/params/{id}", produces = "application/json")
	public String cotillaXML(
	        @PathVariable String id,
	        @RequestParam String nom,
	        @RequestHeader("Accept-Language") String language) { 
	    StringBuilder sb = new StringBuilder();
	    sb.append("Entiende JSON id: " + id + "\n");
	    sb.append("nom: " + nom + "\n");
	    sb.append("language: " + language + "\n");
	    return sb.toString();
	}
	@GetMapping(path = "/params/{id}", params = "pag=1")
	public String cotillaOtro() { 
	    return "Con la pagina";
	}
	@GetMapping("/falla/**")
	//@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String falla() throws Exception { 
		throw new NotFoundException("Forzado");
	   // return "Falla";
	}
	@GetMapping("/sal")
	public ActorShortDTO salida() { 
	    return new ActorShortDTO(1, "Pepito", "Grillo");
	}

	@Autowired
	private RestTemplate srv;
	@Autowired
	private ActoresProxy proxy;
	
	@GetMapping("/extermo")
	public ActorShortDTO pideCatalogo() {
		// return srv.getForObject("http://localhost:8001/actores/1", ActorShortDTO.class);
		return proxy.get(2);
	}
	@GetMapping("/lista")
	public List<ActorShortDTO> pideLista() {
		// return srv.getForObject("http://localhost:8001/actores/1", ActorShortDTO.class);
		return proxy.getAll().getContent();
	}

	@GetMapping(path = "/menu")
	public List<MenuItem> menu() throws URISyntaxException {
		RestTemplate srvRest = new RestTemplate();
		RequestEntity<Void> requestEntity = RequestEntity
				.post(new URI("https://localhost:8243/token?grant_type=password&username=demo&password=P%40%24%24w0rd"))
		        .header("Authorization", "Basic bXlld3dGd1JydE54Zm4yaldmNFhQOHZZWlNBYTpxTElTU2ZwdEJpTDRmSXlGemxOMUpUODlHYzBh")
		        .build();
		AuthDTO auth = srvRest.exchange(requestEntity, AuthDTO.class).getBody();
		RequestEntity<Void> requestMenu = RequestEntity
				.get(new URI("https://localhost:8243/pizzashack/1.0.0/menu"))
		        .header("Authorization", auth.getToken_type() + " " + auth.getAccess_token())
		        .build();
		return srvRest.exchange(requestMenu, new ParameterizedTypeReference<List<MenuItem>>() {}).getBody();
	}

}
