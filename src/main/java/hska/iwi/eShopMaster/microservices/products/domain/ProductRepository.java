package hska.iwi.eShopMaster.microservices.products.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
//	@Query("SELECT p.category_id FROM Product p where p.category_id = id")
	@Query("select u from Product u where u.category_id = ?1")
	List<Product> getProductsWithCategoryId(int id);
	
	@Query("select u from Product u where u.details like %?1% or u.price between ?2 and ?3")
	List<Product> searchForProducts(String details, double minPrice, double maxPrice);
	
}
