package es.urjc.master.practica.entities;

import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {
    
    @Id
    private String name;
    
    private String email;
    private String password;
    
    
    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> roles;

    public User() {
    	;
    }
    
    public User(String name, String email, String password, List<GrantedAuthority> roles) {
        this.name = name;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.roles = roles;
    }

    public void setPassword(String password) {
    	this.password = new BCryptPasswordEncoder().encode(password);
    }
    
    public void setAdmin() {
        GrantedAuthority[] adminRoles = { new SimpleGrantedAuthority("ROLE_ADMIN") };
    	
        this.roles = Arrays.asList(adminRoles);
    }
    
    public void setUser() {
    	GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };
    	
    	this.roles = Arrays.asList(userRoles);    	
    }
    
    @Override
    public String toString() {
    	return String.format("User: %s, Email : %s Role : %s", this.name, this.email,
    			this.roles.toString());
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User u = (User) obj;
		return u.name == this.name && u.roles.equals(this.roles);
	}    
}