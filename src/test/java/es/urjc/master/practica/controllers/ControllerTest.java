package es.urjc.master.practica.controllers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.urjc.master.practica.customers.FilmsRepository;
import es.urjc.master.practica.customers.UserRepository;
import es.urjc.master.practica.entities.Film;
import es.urjc.master.practica.entities.Rating;
import es.urjc.master.practica.entities.User;
import es.urjc.master.practica.services.FilmsRestService;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ControllerTest {
	    
	    @Mock
	    private FilmsRepository filmsDB; 

	    @Mock
	    private FilmsRestService filmsService;
		
	    @Mock
	    private UserRepository usersDB;	    
	    
	    @InjectMocks
	    private VideoClubControllers videoClubController;
	    
	    private MockMvc mockMvc;
	    
	    private User userA, userB;
	    private User admin;
	    
	    private Film filmA, filmB;
	    
	    private ObjectMapper obj;
	    
	    @Before
		public void init(){
	    	videoClubController = new VideoClubControllers();			
			obj = new ObjectMapper();
			mockMvc = MockMvcBuilders.standaloneSetup(videoClubController).build();
			createUsers();
			createFilms();
			MockitoAnnotations.initMocks(this);
		}
	    
	    private void createFilms() {			
			ArrayList<Rating> ratings = new ArrayList<Rating>();

			ratings.add(new Rating());
			ratings.add(new Rating());
			filmA = new Film("Gladiator", "URL_VIDEO", "Gla", "2000", "testDir", "testRep", "url_portada", ratings);
			filmB = new Film("Hercules", "URL_VIDEO", "Her", "1999", "testDir", "testRep", "url_portada", ratings);
	    	
	    }
	    
	    private void createUsers() {
			GrantedAuthority[] adminRoles = { new SimpleGrantedAuthority("ROLE_ADMIN") };
			GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };

			userA = new User("pepe", "pepe@gmail.com", "123456", Arrays.asList(userRoles));
			userB = new User("pepito", "pepito@gmail.com", "key", Arrays.asList(userRoles));
			admin = new User("admin", "admin@gmail.com", "admin...", Arrays.asList(adminRoles));
	    }
	    
	    @Test
	    public void testRoot() throws Exception {
	    	mockMvc.perform(get("/", "/login")
	    		.contentType(MediaType.TEXT_HTML))
	    		.andExpect(status().isOk());
	    }
	    
	    @Test
	    public void testAuth() throws Exception {
	    	mockMvc.perform(get("/home")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    	
	    }
	    	    
/*
	    @Test
	    public void testGetProducts() throws Exception {
	    	
	    	List<Product> products = new ArrayList<>();
	    	products.add(productA);
	    	products.add(productB);
	    	
	    	when(productServiceDB.getProducts()).then(answer -> {
	            return CommonResponse.success(products);
	        });
	    	
	    	mockMvc.perform(get("/products/findAll")
	    		.contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isOk())
	    		.andExpect(jsonPath("$.result.size()", is(2)));

	    }
*/
/*
	    @Test
	    public void testGetProduct() throws Exception {
	    	
	    	when(productServiceDB.getProduct(anyString())).then(answer -> {
	            return CommonResponse.success(productA);
	        });
	    	
	    	mockMvc.perform(get("/products/findByCode/" + productA.getCode())
	    		.contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isOk())
	    		.andExpect(jsonPath("$.result.code", is(productA.getCode())))
	    		.andExpect(jsonPath("$.result.name", is(productA.getName())))
	    		.andExpect(jsonPath("$.result.description", is(productA.getDescription())))
	    		.andExpect(jsonPath("$.result.price", is(productA.getPrice())));

	    }
	    */
	    
/*	    @Test
	    public void testInsertProduct() throws Exception {
	    	
	    	when(productServiceDB.insert(any())).then(answer -> {
	            return CommonResponse.success(productA);
	        });
	    	
	    	mockMvc.perform(post("/products/insert")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(obj.writeValueAsString(productA)))
	    		.andExpect(status().isOk())
	    		.andExpect(jsonPath("$.result.code", is(productA.getCode())))
	    		.andExpect(jsonPath("$.result.name", is(productA.getName())))
	    		.andExpect(jsonPath("$.result.description", is(productA.getDescription())))
	    		.andExpect(jsonPath("$.result.price", is(productA.getPrice())));

	    }
	    
	    */

}
	
