package es.urjc.master.practica.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
	    private ObjectMapper obj;
	    
	    @Before
		public void init(){
	    	videoClubController = new VideoClubControllers();			
			mockMvc = MockMvcBuilders.standaloneSetup(videoClubController).build();
			
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
	    	mockMvc.perform(post("/show?video='test'")
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
	    public void testManagementFilmsGetCreate() throws Exception {
	    	mockMvc.perform(get("/management/films/create")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isMethodNotAllowed());	    	
	    }

	    @Test
	    public void testManagementFilmsPostCreate() throws Exception {
			when(filmsDB.exists(any())).then(answer ->{
				return false;
			});	    	
	    	
			mockMvc.perform(post("/management/films/create")
		    		.contentType(MediaType.TEXT_HTML))
		    		.andExpect(status().isOk());	    	

			when(filmsDB.exists(any())).then(answer ->{
				return true;
			});	    	
			
	    	mockMvc.perform(post("/management/films/create")
		    		.content(obj.writeValueAsString(userA))
	    			.contentType(MediaType.TEXT_HTML))
			    	.andExpect(status().isOk());	    	
		}

	    @Test
	    public void testAddfilm() throws Exception {	    	
	    	mockMvc.perform(get("/addfilm/Test")
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
}
	