package hska.iwi.eShopMaster.microservices.categories.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hska.iwi.eShopMaster.microservices.categories.domain.Category;
import hska.iwi.eShopMaster.microservices.categories.domain.CategoryRepository;

@RestController
public class CategoryController {

	protected CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public ResponseEntity<Category> createCategory(@RequestBody String categoryName) {
		Category createdCategory = categoryRepository.save(new Category(categoryName));
		return new ResponseEntity<Category>(createdCategory, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public List<Category> getCategories() {
		List<Category> categories = categoryRepository.getCategories();
		return categories;
	}
	
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
		categoryRepository.delete(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/categories/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<Category> getCategories(@PathVariable("name") String name) {
		Category category = categoryRepository.findByName(name);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> getCategorie(@PathVariable("id") int id) {
		Category category = categoryRepository.findOne(id);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
}
