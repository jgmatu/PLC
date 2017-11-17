package es.urjc.javsan.master.entities;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {

    private String user;
    private String password;

    private List<GrantedAuthority> roles;

    public User() {
    	;
    }
    
    public User(String name, String password, List<GrantedAuthority> roles) {
        this.user = name;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.roles = roles;
    }
	
	public String getName() {
		return user;
	}

	public void setName(String name) {
		this.user = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(List<GrantedAuthority> roles) {
		this.roles = roles;
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
		return u.user == this.user && u.roles.equals(this.roles);
	}    
}