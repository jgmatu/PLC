package es.urjc.master.practica.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public AuthProviderProducts authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.authorizeRequests()
    					.antMatchers("/css/**", "/js/**", "/images/**","/","/login", "/create/user", "/create/user/new").permitAll()
            			.anyRequest().authenticated();

		http.formLogin()
						
						.loginPage("/login")
						.defaultSuccessUrl("/home",true)
						.failureUrl("/login?error")
						.permitAll()
						.and().exceptionHandling().accessDeniedPage("/denied");

		http.logout()
						
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Authorization
    	auth.authenticationProvider(authenticationProvider);
    }

}