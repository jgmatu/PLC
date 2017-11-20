package es.urjc.master.practica.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.urjc.master.practica.entities.Film;
import es.urjc.master.practica.models.CommonResponse;

@Service
public class FilmsRestService implements FilmsService<Film, String> {

	@Autowired 
	RestTemplate restTemplate;
	
	@Override
	public CommonResponse<Film> getFilm(String title) {
		return null;
	}
}
