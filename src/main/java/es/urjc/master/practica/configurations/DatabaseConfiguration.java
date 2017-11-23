package es.urjc.master.practica.configurations;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import es.urjc.master.practica.customers.UserRepository;
import es.urjc.master.practica.entities.User;

@Component
public class DatabaseConfiguration {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	private void initDatabase() {		
		// Create admin user
		System.out.println("Initialize user db...");
		
		GrantedAuthority[] adminRoles = { new SimpleGrantedAuthority("ROLE_ADMIN") };
		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };
		userRepository.save(new User("root", "root@gmail.com" , "root1", Arrays.asList(adminRoles)));
		userRepository.save(new User("user", "javi@gmail.com","user1", Arrays.asList(userRoles)));
	}
}
