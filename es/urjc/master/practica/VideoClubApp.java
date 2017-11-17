package es.urjc.master.practica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.urjc.master.practica.configuration.UsersMockDB;

@SpringBootApplication
public class VideoClubApp {
	
	@Bean
	public UsersMockDB usersMockDB() {
		return new UsersMockDB();
	}

	public static void main(String[] args) {
		SpringApplication.run(VideoClubApp.class, args);
	}
	
}
