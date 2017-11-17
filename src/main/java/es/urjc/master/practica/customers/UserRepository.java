package es.urjc.master.practica.customers;

import org.springframework.data.repository.CrudRepository;

import es.urjc.master.practica.entities.User;

public interface UserRepository extends CrudRepository<User, String> {

}
