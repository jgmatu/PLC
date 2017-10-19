package es.urjc.javsan.master;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class ManagementProductsDBAccess extends WebMvcConfigurerAdapter {
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource msgSrc = new ResourceBundleMessageSource();
		
		msgSrc.setBasename("messages");
		return msgSrc;
	}
	
	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		
		sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		return sessionLocaleResolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean 
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor result = new LocaleChangeInterceptor();

		result.setParamName("lang");
		return result;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ManagementProductsDBAccess.class, args);
	}
}

