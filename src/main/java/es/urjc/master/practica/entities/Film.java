package es.urjc.master.practica.entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Film {
	
	@Id
    @JsonProperty("Title")
	private String Title;
	
    @JsonProperty("Plot")
    private String Plot;

    @JsonProperty("Year")
	private String Year;
    
    @JsonProperty("Director")
    private String Director;

    @JsonProperty("Actors")
    private String Actors;
    
    @JsonProperty("Poster")
    private String Poster;

    @JsonProperty("Ratings")
    private ArrayList<Rating> Ratings;

	public Film(String Title, String video, String description, String year, String director, String reparto, String portada, ArrayList<Rating> Ratings) {
		this.Title = Title;
		this.Ratings = new ArrayList<Rating>();
	}

	public Film() {
		;
	}
		
	@Override
	public String toString() {
		StringBuffer format = new StringBuffer();

		format.append(String.format("Title : %s Year: %s Director: %s Actors: %s"
				+ "Poster: %s\n", Title, Year, Director, Actors, Poster));

		for (Rating r: Ratings) {
			format.append(String.format("\t%s", r.toString()));
		}		
		return format.toString();
	}
}
