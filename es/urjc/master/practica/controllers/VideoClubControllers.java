package es.urjc.master.practica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoClubControllers {
	
	@RequestMapping(value = {"/", "login"})
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/home")
	public ModelAndView home() {
		return new ModelAndView("films");
	}

	@RequestMapping(value = "/denied")
	public ModelAndView denied() {
		return new ModelAndView("denied");
	}

	
}
