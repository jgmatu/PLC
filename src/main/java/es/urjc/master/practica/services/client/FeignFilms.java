package es.urjc.master.practica.services.client;

import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.master.practica.entities.Film;
import es.urjc.master.practica.models.CommonResponse;
import feign.Headers;
import feign.RequestLine;

@Headers({ "Accept: application/json", "Content-Type: application/json" })
public interface FeignFilms {
 
	final String apiKey = "http://www.omdbapi.com/?t=Gru&apikey=3bdc1f9c";
	
    @RequestLine("GET /findAll?apikey="+apiKey)
    CommonResponse<Film> findFilm(@RequestParam String t);
    
}