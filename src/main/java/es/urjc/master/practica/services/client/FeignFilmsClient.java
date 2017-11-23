package es.urjc.master.practica.services.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import es.urjc.master.practica.entities.Film;

@Headers({ "Accept: application/json", "Content-Type: application/json" })
public interface FeignFilmsClient {
	
    @RequestLine("GET /?t={name}&apiKey={apiKey}")
    Film findOne(@Param("name") String name, @Param("apiKey") String apiKey);
}