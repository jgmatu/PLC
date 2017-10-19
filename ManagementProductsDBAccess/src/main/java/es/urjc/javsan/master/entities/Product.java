package es.urjc.javsan.master.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {

	@Id
    @Min(0)
	private int code;

    @Size(min=2, max=30)
	private String name;
    
    @Size(min=0, max=120)
    private String desc;

    @Min(0)
    private float price;
	
	public Product() {
		;
	}
	
	public Product(int code, String name, String desc, float price) {
		this.code = code;
		this.name = name;
		this.desc = desc;
		this.price = price;
	}
	
	@Override 
	public String toString() {
		return String.format("Code : %d Name : %s, Desc : %s, Price : %f", code, name, desc, price);
	}
}
