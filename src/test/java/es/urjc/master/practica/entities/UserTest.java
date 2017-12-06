package es.urjc.master.practica.entities;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserTest {

	private User userA, userB;
	
	@Before
	public void init() {
		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };

		userA = new User("Test", "Test", "Test", Arrays.asList(userRoles));
		userB = new User("Test", "Test", "Test", Arrays.asList(userRoles));
	}
	
	@Test
	public void test() {
		assertEquals(userA, userB);		
		
		userA.setAdmin();
		assertNotEquals(userA, userB);		
		
		userB.setAdmin();
		assertEquals(userA, userB);
		
		userA.setName("");
		assertNotEquals(userA, userB);		
		
		userB.setName("");
		assertEquals(userA, userB);		
		
		assertEquals(userA, userA);
		
		Film film = new Film();
		
		assertNotEquals(userA, film);
		
		userB.setPassword("Bla");
		assertNotEquals("", userB.getPassword());

		
		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };
		userB.setRoles(Arrays.asList(userRoles));
		userA.setRoles(Arrays.asList(userRoles));
		assertEquals(userA.getRoles(), userB.getRoles());
	}

}
