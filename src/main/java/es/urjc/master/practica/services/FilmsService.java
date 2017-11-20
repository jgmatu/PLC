package es.urjc.master.practica.services;

import es.urjc.master.practica.entities.Film;
import es.urjc.master.practica.models.CommonResponse;


public interface FilmsService <T,K> {
    public CommonResponse<Film> getFilm(K code);
}
