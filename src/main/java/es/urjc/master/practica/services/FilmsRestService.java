package es.urjc.master.practica.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.master.practica.entities.Film;
import es.urjc.master.practica.services.client.FeignFilmsClient;

@Service
public class FilmsRestService implements FilmsService<Film, String> {

	private static final String API_KEY = "3bdc1f9c";
	
	@Autowired 
	FeignFilmsClient feignFilms;
	
	@Override
	public Film getFilm(String title) {
		return feignFilms.findOne(title, API_KEY);
	}
}
