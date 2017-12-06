package es.urjc.master.practica.configurations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringRunner;

import es.urjc.master.practica.customers.UserRepository;
import es.urjc.master.practica.entities.User;

@RunWith(SpringRunner.class)
public class AuthProviderTest {
	
	@Mock
	private UserRepository usersDB;
	
	@InjectMocks
	private AuthenticationProviderConfiguration authProducts;
	
	@Before
	public void init() {
		authProducts = new AuthenticationProviderConfiguration();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void authTest() {
		User user = new User("user", "user@gmail.com", "user1", null); 
		
		when(usersDB.findOne(user.getName())).then(answer ->{
			return user;
        });
		authProducts.authenticate(new AuthTestImp(user.getName(), "user1"));

		when(usersDB.findOne(user.getName())).then(answer ->{
			return null;
        });
		try {
			authProducts.authenticate(new AuthTestImp(user.getName(), "user1"));
			fail("Bad User...");
		} catch (BadCredentialsException e) {
			;
		}
		
		when(usersDB.findOne(user.getName())).then(answer ->{
			return user;
        });
		try {
			authProducts.authenticate(new AuthTestImp(user.getName(), "userFail"));	
			fail("Bad Password...");
		} catch (BadCredentialsException e) {
			
		}
	}
}
