package es.urjc.master.practica.customers;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import es.urjc.master.practica.entities.User;

@Service
public class UsersDB {
	
	@Autowired
	private UserRepository usersRepo;
	
    @PostConstruct
	private void initDatabase() {	
		System.out.println("Data base users initialized...");

		SimpleGrantedAuthority[] userRoles = {new SimpleGrantedAuthority("ROLE_USER")};
		usersRepo.save(new User("user", "user@gmail.com", "user1", Arrays.asList(userRoles)));
	
		SimpleGrantedAuthority[] adminRoles = {new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN")};
		usersRepo.save(new User("root", "root@gmail.com", "root1", Arrays.asList(adminRoles)));
	}	
}
