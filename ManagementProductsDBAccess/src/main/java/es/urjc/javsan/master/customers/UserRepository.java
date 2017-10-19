package es.urjc.javsan.master.customers;

import org.springframework.data.repository.CrudRepository;
import es.urjc.javsan.master.entities.User;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUser(String user);
}
