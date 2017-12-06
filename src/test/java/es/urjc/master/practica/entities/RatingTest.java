package es.urjc.master.practica.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RatingTest {

	private Rating ratingA, ratingB;

	@Before
	public void init() {
		ratingA = new Rating("TestA", "TestA");
		ratingB = new Rating("TestB", "TestB");
	}
	
	@Test
	public void testRating() {
		assertNotEquals(ratingA.getSource(), ratingB.getSource());
		assertNotEquals(ratingA.getValue(), ratingB.getValue());
		
		ratingA.setValue(ratingB.getValue());
		ratingA.setSource(ratingB.getSource());
		
		assertEquals(ratingA.getSource(), ratingB.getSource());
		assertEquals(ratingA.getValue(), ratingB.getValue());
	}
}
