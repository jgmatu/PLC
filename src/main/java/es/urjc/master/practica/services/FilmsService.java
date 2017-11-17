package es.urjc.master.practica.services;

import es.urjc.master.practica.models.CommonResponse;


public interface FilmsService <T,K> {
    public CommonResponse<T> getFilm(K code);
}
