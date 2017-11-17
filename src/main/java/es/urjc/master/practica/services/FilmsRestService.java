package es.urjc.master.practica.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.master.practica.entities.Film;
import es.urjc.master.practica.models.CommonResponse;
import es.urjc.master.practica.services.client.FilmsFeign;

@Service
public class FilmsRestService implements FilmsService<Film, String> {
		
	@Override
	public CommonResponse<Film> getFilm(String title) {
		return null;
	}
}
