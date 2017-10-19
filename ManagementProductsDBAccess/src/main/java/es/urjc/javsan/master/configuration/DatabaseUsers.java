package es.urjc.javsan.master.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import es.urjc.javsan.master.customers.UserRepository;
import es.urjc.javsan.master.entities.User;

@Service
public class DatabaseUsers {
	
	@Autowired
	private UserRepository userRepository;
	
    @PostConstruct
	private void initDatabase() {	
		System.out.println("Data base users initialized...");

		SimpleGrantedAuthority[] userRoles = {new SimpleGrantedAuthority("ROLE_USER")};
		userRepository.save(new User("user", "user1", Arrays.asList(userRoles)));
	
		SimpleGrantedAuthority[] adminRoles = {new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN")};
		userRepository.save(new User("root", "root1", Arrays.asList(adminRoles)));
	}	
}
