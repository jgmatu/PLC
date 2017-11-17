package es.urjc.master.practica.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Film {

	public enum Value {WORSE, BAD, NORMAL, GOOD, INCREDIBLE};
	
	@Id
	private String name;
	
	private String video;
	private String description;
	private int year;
	private String director;
	private String reparto;
	private String portada;
	private Value value;

	
	public Film(String name, String video, String description, int year, String director, String reparto, String portada, Value value) {
		this.name = name;
		this.video = video;
		this.description = description;
		this.year = year;
		this.director = director;
		this.reparto = reparto;
		this.portada = portada;
		this.value = value;
	}

	protected Film() {
		;
	}
	
	
	@Override
	public String toString() {
		return "";
	}
}
