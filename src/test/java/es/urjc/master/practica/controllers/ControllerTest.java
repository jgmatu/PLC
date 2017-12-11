package es.urjc.master.practica.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
	    
	    private User userA;
	    private Film filmA;
	    private ObjectMapper obj;
	    
	    @Before
		public void init(){
	    	videoClubController = new VideoClubControllers();			
			mockMvc = MockMvcBuilders.standaloneSetup(videoClubController).build();
			
			ArrayList<Rating> ratings = new ArrayList<Rating>();
			ratings.add(new Rating("bla", "la"));
			ratings.add(new Rating("test", "test"));
			filmA = new Film("Gladiator", "URL_VIDEO", "Gla", "2000", "testDir", "testRep", "url_portada", ratings, "www.yotube.com", "A");

			GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };	
			userA = new User("Test", "Test", "Test", Arrays.asList(userRoles));
			obj = new ObjectMapper();
			
			MockitoAnnotations.initMocks(this);
		}
	    	    
	    @Test
	    public void testRoot() throws Exception {
	    	mockMvc.perform(get("/", "/login")
	    		.contentType(MediaType.TEXT_HTML))
	    		.andExpect(status().isOk());
	    }
	    
	    @Test
	    public void testHome() throws Exception {
	    	mockMvc.perform(get("/home")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    	
	    }

	    @Test
	    public void testDenied() throws Exception {
	    	mockMvc.perform(get("/denied")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    	
	    }

	    @Test
	    public void testShow() throws Exception {
	    	mockMvc.perform(post("/view/film/test")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    	
	    }

	    @Test
	    public void testManagementFilms() throws Exception {
	    	mockMvc.perform(get("/management/films")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    	
	    }

	    @Test
	    public void testManagementFilmsPostCreate() throws Exception {
			when(filmsDB.exists(any())).then(answer -> {
				return false;
			});	    	
	    	
			mockMvc.perform(post("/management/films")
		    		.content(obj.writeValueAsString(filmA))
					.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    	
			
			when(filmsDB.exists(any())).then(answer -> {
				return true;
			});	    	
			
	    	mockMvc.perform(post("/management/films")
		    		.content(obj.writeValueAsString(filmA))
	    			.contentType(MediaType.TEXT_HTML))
			    	.andExpect(status().isOk());	    	
		}

	    @Test
	    public void testAddfilm() throws Exception {	    	
			when(filmsService.getFilm(any())).then(answer ->{
				return filmA;
			});	    	

	    	mockMvc.perform(get("/addfilm/test")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    	
	    }

	    @Test
	    public void testAddfilmFail() throws Exception {	    	
	    	mockMvc.perform(get("/addfilm")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isNotFound());	    	
	    }

	    @Test
	    public void deleteFilm() throws Exception {
	    	mockMvc.perform(get("/delete/film/Test")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().is3xxRedirection());	    		    	
	    }

	    @Test
	    public void deleteFilmFail() throws Exception {
	    	mockMvc.perform(get("/delete/film")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isNotFound());	    		    	
	    }
	    
	    @Test
	    public void viewFilmTest() throws Exception {
	    	mockMvc.perform(get("/view/film/Test")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    		    		    	
	    }
	    
	    @Test
	    public void createNewUser() throws Exception {
	    	mockMvc.perform(get("/create/user/new")
	    			.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    		    		    	
		}

	    @Test
	    public void managementUser() throws Exception {
	    	mockMvc.perform(get("/management/users")
	    			.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    		    		    	
		}

	    @Test
	    public void managementDeleteUser() throws Exception {
	    	mockMvc.perform(get("/delete/user/Test")
	    			.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().is3xxRedirection());	    		    		    	
		}
	    
	    @Test
	    public void managementUserAdmin() throws Exception {
	    	mockMvc.perform(get("/user/admin/Test")
	    			.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    		    		    	
		}

	    @Test
	    public void managementUserGetUpdate() throws Exception {
	    	mockMvc.perform(get("/user/update")
	    			.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isMethodNotAllowed());	    		    		    	
	    }
	    
	    @Test
	    public void managementUserPostUpdate() throws Exception {   	
	    	when(usersDB.findOne(any())).then(answer ->{
				return userA;
			});	    	
	    	
	    	mockMvc.perform(post("/user/update")	
	    			.contentType(MediaType.MULTIPART_FORM_DATA))
		    		.andExpect(status().isOk());	    		    		    	
		    verify(usersDB, times(1)).save(userA);
		    verify(usersDB, times(1)).delete(userA);
	    }
	    
	    @Test
	    public void searchOneTest() throws Exception {
			when(filmsDB.findOne(any())).then(answer -> {
				return filmA;
			});	    	
	 
			mockMvc.perform(post("/home")	
	    			.contentType(MediaType.MULTIPART_FORM_DATA))
		    		.andExpect(status().isOk());	
			
		    verify(filmsDB, times(1)).findOne(any());	    	
	    }

	    @Test
	    public void searchIGetTest() throws Exception {
	    	when(filmsDB.findAll()).then(answer -> {
		    	ArrayList<Film> films = new ArrayList<>();
		    	
	    		films.add(filmA);
				return films;
			});	    	
	 
			mockMvc.perform(get("/inactive/films")	
	    			.contentType(MediaType.MULTIPART_FORM_DATA))
		    		.andExpect(status().isOk());	
		    verify(filmsDB, times(1)).findAll();	    	
	    }
	

	    @Test
	    public void searchIPostTest() throws Exception {
			when(filmsDB.findOne(any())).then(answer -> {
				return filmA;
			});	    	
	 
			mockMvc.perform(post("/inactive/films")	
	    			.contentType(MediaType.MULTIPART_FORM_DATA))
		    		.andExpect(status().isOk());	  	
	    }
	    
	    @Test
	    public void updateUrlOneTest() throws Exception {
			when(filmsDB.findOne(any())).then(answer -> {
				return filmA;
			});	    	
	 
			mockMvc.perform(post("/film/update")	
		    		.content("urlt")
					.contentType(MediaType.MULTIPART_FORM_DATA))
		    		.andExpect(status().isOk());		    	
	    }

	    @Test
	    public void updateUrlAllTest() throws Exception {
			when(filmsDB.findOne(any())).then(answer -> {
				return filmA;
			});	    	
	 
			mockMvc.perform(post("/film/update/all")	
		    		.content("urlt")
					.contentType(MediaType.MULTIPART_FORM_DATA))
		    		.andExpect(status().isOk());		    	
	    }

	    @Test
	    public void editFilmTest() throws Exception {
			when(filmsDB.findOne(any())).then(answer -> {
				return filmA;
			});	    	
	 
			mockMvc.perform(post("/edit/film/test")	
		    		.content("urlt")
					.contentType(MediaType.MULTIPART_FORM_DATA))
		    		.andExpect(status().isOk());		    	
	    }

	    @Test
	    public void createUserTest() throws Exception {
			mockMvc.perform(get("/create/user")	
					.contentType(MediaType.MULTIPART_FORM_DATA))
		    		.andExpect(status().isOk());
	    }
}
	
