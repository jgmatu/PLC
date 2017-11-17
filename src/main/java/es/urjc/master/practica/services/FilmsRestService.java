package es.urjc.master.practica.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.master.practica.entities.Film;
import es.urjc.master.practica.models.CommonResponse;
import es.urjc.master.practica.services.client.FeignFilms;

@Service
public class FilmsRestService implements FilmsService<Film, String> {

	@Autowired
	FeignFilms feignFilms;
		
	@Override
	public CommonResponse<Film> getFilm(String title) {
		return feignFilms.findFilm(title);
	}
	
}
