package es.urjc.master.practica.controllers;


import java.util.ArrayList;
import java.util.List;

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
		return new ModelAndView("denied_page");
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping("/home")
	public ModelAndView search() {
		return new ModelAndView("films").addObject("films", filmsDB.findAll());
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView searchOne(String title) {
		List<Film> filmOne = new ArrayList<Film>();
		filmOne.add(filmsDB.findOne(title));
		return new ModelAndView("films").addObject("films", filmOne);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("management/films")
	public ModelAndView managementFilms() {
		return new ModelAndView("management_films");
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "management/films", method = RequestMethod.POST)
	public ModelAndView managementFilmsCreate(String title) {
		if (filmsDB.exists(title)) {
			return new ModelAndView("management_films")
					.addObject("result", "\r\n" + 
							"Film exists in the database")
					.addObject("film", filmsDB.findOne(title));
		}	
		Film film = filmsService.getFilm(title);
		return new ModelAndView("management_films")
				.addObject("result", "Film does not exist in the database")
				.addObject("film", film);
	}

	/*@Secured("ROLE_ADMIN")
	@RequestMapping(value = "management/films/create", method = RequestMethod.POST)
	public ModelAndView managementFilmsCreate(String title, String url) {
		if (filmsDB.exists(title)) {
			return new ModelAndView("management_create")
					.addObject("result", "inDB")
					.addObject("film", filmsDB.findOne(title));
		}	
		Film film = filmsService.getFilm(title);
		return new ModelAndView("management_create")
				.addObject("result", "outDB")
				.addObject("film", film);
	}*/	


	@Secured({"ROLE_ADMIN"})
    @RequestMapping("/addfilm/{Title}")
    public ModelAndView saveFilm(@PathVariable String Title){
			Film film = filmsService.getFilm(Title);
			filmsDB.save(film);
			return new ModelAndView("url_film")
					.addObject("title", Title);	
    }
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/film/update", method = RequestMethod.POST)
	public String updateUrl(Model model, String title, String urlt) {
		Film film = filmsDB.findOne(title);
		filmsDB.delete(film);
		String[] partes = urlt.split("=");
		String part1 = partes[0]; 
		String part2 = partes[1]; 
		film.setTrailer(part2);
		filmsDB.save(film);
		model.addAttribute("message", "Saved film");
        return "success";
	}
	
	/*@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/film/update/all", method = RequestMethod.POST)
	public String updateUrl(Model model, String title, String urlt) {
		Film film = filmsDB.findOne(title);
		filmsDB.delete(film);
		String[] partes = urlt.split("=");
		String part1 = partes[0]; 
		String part2 = partes[1]; 
		film.setTrailer(part2);
		filmsDB.save(film);
		model.addAttribute("message", "Saved film");
        return "success";
	}*/
	
	
	
	
	
	
	

	
	@Secured({"ROLE_ADMIN"})
    @RequestMapping("/delete/film/{Title}")
    public String deleteFilm(@PathVariable String Title){
		filmsDB.delete(Title);
        return "redirect:/home";
    }

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("/view/film/{Title}")
    public ModelAndView viewFilm(@PathVariable String Title){
		return new ModelAndView("show_film").addObject("film", filmsDB.findOne(Title));
    }
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("/edit/film/{Title}")
	public ModelAndView editFilm(@PathVariable String Title){
		return new ModelAndView("edit_film").addObject("film", filmsDB.findOne(Title));
    }
	

	@RequestMapping("/create/user")
	public ModelAndView createUser() {
		return new ModelAndView("create_user").addObject("user", new User());
	}

	@RequestMapping("/create/user/new")   
	public String newUser(@Valid User user) {
		user.setUser();
		usersDB.save(user);
		return "/login";
	}
	

	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/management/users")
	public ModelAndView managementUsers() {
		return new ModelAndView("management_users")
				.addObject("users", new Film()).addObject("users", usersDB.findAll());
	}

	@Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/management/users", method = RequestMethod.POST)
	public ModelAndView listOneuser(String user){
		List<User> userOne = new ArrayList<User>();
		userOne.add(usersDB.findOne(user));
		return new ModelAndView("management_users").addObject("users", userOne);
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
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public String updateAdmin(Model model, String name, String email, boolean checkadmin) {
		User user = usersDB.findOne(name);
		usersDB.delete(user);
		if (checkadmin) {
			user.setAdmin();
		} else {
			user.setUser();
		}
		user.setEmail(email);
		usersDB.save(user);
		model.addAttribute("users", usersDB.findAll());
        return "management_users";
	}
}
