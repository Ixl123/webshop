package hska.iwi.eShopMaster.microservices.products.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import hska.iwi.eShopMaster.model.database.dataobjects.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	Product findById(int id);
	
}
