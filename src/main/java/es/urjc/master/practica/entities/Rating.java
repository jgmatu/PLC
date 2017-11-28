package es.urjc.master.practica.entities;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class Rating {

	@JsonProperty("Source")
	private String Source;

	@JsonProperty("Value")
	private String Value;

	@Override
	public String toString() {
		return String.format("Source : %s Value : %s\n", Source, Value);
	}
}
