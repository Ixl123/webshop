package hska.iwi.eShopMaster.microservices.categories.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
	
//	public Category save(Category category);
	
	@Query("SELECT u from Category u")
	public List<Category> getCategories();
	
	public Category findByName(String name);

}
