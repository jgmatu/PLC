package es.urjc.javsan.master.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.javsan.master.database.DatabaseProducts;
import es.urjc.javsan.master.entities.Product;

@Controller
public class ControllerDatabaseAccess {
		
	@Autowired
	private DatabaseProducts productDatabase;

	@RequestMapping("/")
	public ModelAndView index() {				
		return new ModelAndView("index");
	}

	@RequestMapping("/login")
	public ModelAndView login() {				
		return new ModelAndView("login");
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("home");
		return model;
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/add") 
	public ModelAndView edit(Product product) {
		return new ModelAndView("form_product");
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/add")
    public ModelAndView addSubmit(@Valid Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("form_product");
		}
		productDatabase.add(product);
		return new ModelAndView("home");
    }

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/edit") 
	public ModelAndView add(@RequestParam int code) {
		return new ModelAndView("form_edit")
				.addObject("product", productDatabase.get(code));
	}

	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/edit")
    public ModelAndView editSubmit(@Valid Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("form_edit");
		}	
		productDatabase.edit(product);
		return new ModelAndView("home");
    }

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("list_products")
				.addObject("productService", productDatabase.findAll());		
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam int code) {
		productDatabase.delete(code);
		return new ModelAndView("greeting_template");
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping("/product")
	public ModelAndView product(@RequestParam int code) {
		return new ModelAndView("product")
				.addObject("product", productDatabase.get(code));		
	}
}