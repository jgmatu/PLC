package es.urjc.master.practica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.urjc.master.practica.services.FilmsRestService;

@SpringBootApplication
public class VideoClubApp {
	
	public static void main(String[] args) {
		SpringApplication.run(VideoClubApp.class, args);
	}
	
}
