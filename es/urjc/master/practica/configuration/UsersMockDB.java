package es.urjc.master.practica.configuration;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import es.urjc.javsan.master.entities.User;

public class UsersMockDB {
	
	private HashMap<String, User> users;
	
	public UsersMockDB() {
		users = new HashMap<>();

		System.out.println("Data base users initialized...");

		SimpleGrantedAuthority[] userRoles = {new SimpleGrantedAuthority("ROLE_USER")};
		users.put("user", new User("user", "user1", Arrays.asList(userRoles)));
	
		SimpleGrantedAuthority[] adminRoles = {new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN")};
		users.put( "root", new User("root", "root1", Arrays.asList(adminRoles)));
		
	}
	
	public User getName(String user) {
		return users.get(user);
	}
	
}
