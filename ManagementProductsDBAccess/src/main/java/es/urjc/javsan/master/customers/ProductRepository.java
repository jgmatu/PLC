package es.urjc.javsan.master.customers;

import org.springframework.data.repository.CrudRepository;
import es.urjc.javsan.master.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	;
}
