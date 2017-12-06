package es.urjc.master.practica.services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import es.urjc.master.practica.entities.Film;
import es.urjc.master.practica.entities.Rating;
import es.urjc.master.practica.services.client.FeignFilmsClient;


@RunWith(SpringRunner.class)
public class FilmsRestServiceTest {

	@Mock
	private FeignFilmsClient feignFilms;
	
	@InjectMocks
	private FilmsRestService filmsService;
	
	@Before
	public void init() {
		filmsService = new FilmsRestService();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addProductTest() {
		Film film = getFilmTest();
		
		when(feignFilms.findOne(any(), any())).then(answer ->{
			return film;
		});
		assertEquals(film, filmsService.getFilm("Test"));
	    verify(feignFilms, times(1)).findOne(any(), any());
	}
	
	private Film getFilmTest() {
		ArrayList<Rating> ratings = new ArrayList<Rating>();

		ratings.add(new Rating("bla", "la"));
		ratings.add(new Rating("test", "test"));

		return new Film("Gladiator", "URL_VIDEO", "Gla", "2000", "testDir", "testRep", "url_portada", ratings);
	}
}
