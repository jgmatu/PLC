package es.urjc.master.practica.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.urjc.master.practica.services.client.FeignFilmsClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;


@Configuration
public class BeansConfiguration{
	
	private static final String BACK_URL = "http://www.omdbapi.com/";
	
	@Bean
    public FeignFilmsClient productServiceFeign() {
        return createService(FeignFilmsClient.class, "");
    }

	
    private <T> T createService(Class<T> clazz, String path) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return Feign.builder()
        		.encoder(new JacksonEncoder(mapper))
        		.decoder(new JacksonDecoder(mapper))
        		.target(clazz, BACK_URL + path);
    }

}
