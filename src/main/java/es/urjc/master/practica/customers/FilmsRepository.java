package es.urjc.master.practica.customers;

import org.springframework.data.repository.CrudRepository;
import es.urjc.master.practica.entities.Film;

public interface FilmsRepository extends CrudRepository<Film, String> {
	
}
