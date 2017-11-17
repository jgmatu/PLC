
package es.urjc.master.practica.customers;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.master.practica.entities.Film;

@Service
public class FilmsDB {
	
	@Autowired
	private FilmsRepository filmsRepository;
	
    @PostConstruct
	private void initDatabase() {	
    	System.out.println("Films created...");
    	
    	filmsRepository.save(new Film("Gru", "https://www.youtube.com/embed/mHalwWgqJTo", "Mi villano favorito", 2017, "Bla", "Bla", "Portada", Film.Value.INCREDIBLE));
    	filmsRepository.save(new Film("Gladiator", "https://www.youtube.com/embed/Q-b7B8tOAQU", "Fuerza y Honor", 2017, "Bla", "Bla", "Portada", Film.Value.INCREDIBLE));
    	filmsRepository.save(new Film("Coach Carter", "https://www.youtube.com/embed/znyAnWUYf2g", "Una de baloncesto", 2017, "Bla", "Bla", "Portada", Film.Value.INCREDIBLE));
    }	
}

