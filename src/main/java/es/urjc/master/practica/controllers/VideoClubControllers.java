package es.urjc.master.practica.controllers;


import java.util.Arrays;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import es.urjc.master.practica.customers.FilmsRepository;
import es.urjc.master.practica.customers.UserRepository;
import es.urjc.master.practica.entities.Film;
import es.urjc.master.practica.entities.User;
import es.urjc.master.practica.services.FilmsRestService;

@Controller
public class VideoClubControllers {
	
	@Autowired 
	private FilmsRepository filmsDB; 

	@Autowired
	private FilmsRestService filmsService;
	
	@Autowired
	private UserRepository usersDB;

	@RequestMapping(value = {"/", "/login"})
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/denied")
	public ModelAndView denied() {
		return new ModelAndView("denied");
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping(value = "/home", method= RequestMethod.GET)
	public ModelAndView search() {
		return new ModelAndView("films").addObject("films", filmsDB.findAll());
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView resultSearch(@RequestParam String name) {
		Film film = filmsDB.findOne(name);
		
		if (film == null) {
			return new ModelAndView("films"); 
		} else {
			return new ModelAndView("films").addObject("films", Arrays.asList(film));			
		}
	}	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping(value = "show")
	public ModelAndView show(@RequestParam String video) {
		return new ModelAndView("show").addObject("video", video);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "management/films")
	public ModelAndView managementFilms() {
		return new ModelAndView("management_films")
				.addObject("film", new Film()).addObject("hidden", true);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "management/films/create", method = RequestMethod.POST)
	public ModelAndView managementFilmsCreate(@RequestParam String title, @RequestParam String url) {
		if (filmsDB.exists(title)) {
			return new ModelAndView("management_create")
					.addObject("result", "Film Already Exist")
					.addObject("film", filmsDB.findOne(title));
		}	
		Film film = filmsService.getFilm(title);
		filmsDB.save(film);
		return new ModelAndView("management_create")
				.addObject("result", "Film Added!!!")
				.addObject("film", film);
	}
	
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping(value = "/management/films/confirm", method = RequestMethod.POST)
	public ModelAndView confirmHome(@RequestParam String Title) {
		Film film = filmsDB.findOne(Title);
		if (film == null) {
			return new ModelAndView("films"); 
		} else {
			return new ModelAndView("films").addObject("films", Arrays.asList(film));			
		}	
	}
	
	
	/*
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "management/users", method = RequestMethod.GET)
	public ModelAndView managmenetUsers() {
		return new ModelAndView("management_users")
				.addObject("user", new User()).addObject("admin", true);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "management/users", method = RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, boolean admin) {
		if (admin) {
			user.setAdmin();
		} else {
			user.setUser();
		}
		usersDB.save(user);
		return new ModelAndView("films");
	}
	}*/
	
	@RequestMapping(value = "/create/user", method = RequestMethod.GET)
	public ModelAndView createUser() {
		return new ModelAndView("create_user")
				.addObject("user", new User());
	}

	@RequestMapping("/create/user/new")   
	public String newUser(@Valid User user) {
		user.setUser();
		usersDB.save(user);
		return "login";
	}

	
	@Secured("ROLE_ADMIN")
    @RequestMapping("/management/users")
	public String listusers(Model model){
		model.addAttribute("users", usersDB.findAll());
        return "management_users";
    }	

	@Secured({"ROLE_ADMIN"})
    @RequestMapping("/delete/user/{name}")
    public String borrar(@PathVariable String name){
		usersDB.delete(name);
        return "redirect:/management/users";
    }

	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/user/admin/{name}")
	public String userAdmin(@PathVariable String name, Model model) {	
		model.addAttribute("user", usersDB.findOne(name));
		return "edit_user";
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/user/update")
	public String updateAdmin(@Valid User user,  boolean checkadmin) {
		if (checkadmin) {
			user.setAdmin();
		} else {
			user.setUser();
		}
		usersDB.save(user);
		return "management_users";
	}

	

}
