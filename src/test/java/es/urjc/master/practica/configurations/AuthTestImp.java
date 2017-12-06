package es.urjc.master.practica.configurations;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AuthTestImp implements Authentication{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;

	public AuthTestImp(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return this.password;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return false;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
			;
	}

}
