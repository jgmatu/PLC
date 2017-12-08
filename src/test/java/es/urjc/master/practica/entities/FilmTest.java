package es.urjc.master.practica.entities;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class FilmTest {

	private Film filmA, filmB;
	
	@Before
	public void init() {
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ratings.add(new Rating("bla", "la"));
		ratings.add(new Rating("test", "test"));
		
		filmA = new Film("Gladiator", "URL_VIDEO", "Gla", "2000", "testDir", "testRep", "url_portada", ratings, "www.yotube.com");
		filmB = new Film("Hercules", "URL_VIDEO", "Her", "1999", "testDir", "testRep", "url_portada", ratings, "www.yotube.com");
	}
	
	@Test
	public void test() {
		assertNotEquals(filmA, filmB);
		
		filmB.setActors(filmA.getActors());
		filmB.setDirector(filmA.getDirector());
		filmB.setPlot(filmA.getPlot());
		filmB.setPoster(filmA.getPoster());
		filmB.setTitle(filmA.getTitle());
		filmB.setYear(filmA.getYear());
		filmB.setRatings(filmA.getRatings());
		filmB.setTrailer(filmA.getTrailer());

		assertEquals(filmA.getDirector(), filmB.getDirector());
		assertEquals(filmA.getActors(), filmB.getActors());
		assertEquals(filmA.getPlot(), filmB.getPlot());
		assertEquals(filmA.getPoster(), filmB.getPoster());
		assertEquals(filmA.getTitle(), filmB.getTitle());
		assertEquals(filmA.getYear(), filmB.getYear());
	}

}
