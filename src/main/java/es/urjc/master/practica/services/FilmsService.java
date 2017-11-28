package es.urjc.master.practica.services;


import es.urjc.master.practica.entities.Film;

public interface FilmsService <T,K> {
    public Film getFilm(K name);
}
