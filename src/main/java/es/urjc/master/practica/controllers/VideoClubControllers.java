package es.urjc.master.practica.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import es.urjc.master.practica.customers.FilmsRepository;
import es.urjc.master.practica.customers.UserRepository;
import es.urjc.master.practica.entities.Film;
import es.urjc.master.practica.entities.User;
import es.urjc.master.practica.services.FilmsRestService;

@Controller
public class VideoClubControllers {
	
	private static final String USER = "user";
	private static final String USERS = "users";

	private static final String FILM = "film";
	private static final String FILMS = "films";
	private static final String RESULT = "result";
	
	
	private static final String VIEW_FILMS_PAGE = "films_page";
	private static final String VIEW_FILMS_INACTIVE = "films_inactive";
	
	
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
		return new ModelAndView(VIEW_FILMS_PAGE).addObject(FILMS, filmsDB.findAll());
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView searchOne(String title) {
		return new ModelAndView(VIEW_FILMS_PAGE).addObject(FILMS, filmsDB.findOne(title));
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping("/inactive/films")
	public ModelAndView searchI() {
		return new ModelAndView(VIEW_FILMS_INACTIVE).addObject(FILMS, filmsDB.findAll());
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping(value = "/inactive/films", method = RequestMethod.POST)
	public ModelAndView searchOneI(String title) {
		return new ModelAndView(VIEW_FILMS_INACTIVE).addObject(FILMS, filmsDB.findOne(title));
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
					.addObject(RESULT, "Film exists in the database")
					.addObject(FILM, filmsDB.findOne(title));
		}
		
		Film film = filmsService.getFilm(title);
		return new ModelAndView("management_films")
				.addObject(RESULT, "Film does not exist in the database")
				.addObject(FILM, film);
	}

	@Secured({"ROLE_ADMIN"})
    @RequestMapping("/addfilm/{title}")
    public ModelAndView saveFilm(@PathVariable String title){
			Film film = filmsService.getFilm(title);
	
			film.setEstado("A");
			filmsDB.save(film);
			return new ModelAndView("url_film")
					.addObject("title", title);	
    }
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/film/update", method = RequestMethod.POST)
	public String updateUrl(Model model, String title, String urlt) {		
		Film film = filmsDB.findOne(title);
		String[] splitUrl = urlt.split("=");

		if (splitUrl.length != 2) {
			return "url_film";
		}
		filmsDB.save(getVideoTrailerFilm(film, splitUrl[1]));
		model.addAttribute("message", "Saved film");
        return "success";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/film/update/all", method = RequestMethod.POST)
	public String updateUrl(Model model, String title, boolean checkOn, String poster, String trailer) {		
		Film film = filmsDB.findOne(title);
		String[] splitUrl = trailer.split("=");

		if (splitUrl.length != 2) {
			return "url_film";
		}
		filmsDB.save(getFilmUpdateState(film, poster, checkOn));
		model.addAttribute("message", "Saved film");
        return "success";
	}
	
	@Secured({"ROLE_ADMIN"})
    @RequestMapping("/delete/film/{title}")
    public String deleteFilm(@PathVariable String title){
		filmsDB.delete(title);
        return "redirect:/home";
    }

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("/view/film/{title}")
    public ModelAndView viewFilm(@PathVariable String title) {
		return new ModelAndView("show_film").addObject("film", filmsDB.findOne(title));
    }
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("/edit/film/{title}")
	public ModelAndView editFilm(@PathVariable String title){
		return new ModelAndView("edit_film").addObject(FILM, filmsDB.findOne(title));
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
				.addObject(USERS, usersDB.findAll());
	}

	@Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/management/users", method = RequestMethod.POST)
	public ModelAndView listOneuser(String user){
		return new ModelAndView("management_users").addObject(USERS, usersDB.findOne(user));
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
		model.addAttribute(USER, usersDB.findOne(name));
		return "edit_user";
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public String updateAdmin(Model model, String name, String email, boolean checkadmin) {
		User userCache = getUpdateUser(name, email, checkadmin);
		usersDB.delete(usersDB.findOne(name));
		usersDB.save(userCache);

		model.addAttribute(USERS, usersDB.findAll());
        return "management_users";
	}
	
	private User getUpdateUser(String name, String email, boolean checkAdmin) {
		User user = usersDB.findOne(name);
		
		if (checkAdmin) {
			user.setAdmin();
		} else {
			user.setUser();
		}
		user.setEmail(email);		
		return user;
	}
	
	private Film getVideoTrailerFilm(Film film, String idVideo) {
		film.setTrailer(idVideo);
		return film;
	}
	
	private Film getFilmUpdateState(Film film, String poster, boolean isActive) {
		film.setPoster(poster);
		if(isActive) {
			film.setEstado("A");
			
		}else {
			film.setEstado("I");
		}
		return film;
	}
	
}
