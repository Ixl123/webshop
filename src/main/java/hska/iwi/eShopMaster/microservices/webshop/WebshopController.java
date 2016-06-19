package hska.iwi.eShopMaster.microservices.webshop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import hska.iwi.eShopMaster.microservices.categories.domain.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
//import hska.iwi.eShopMaster.microservices.products.domain.Product;
//import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

@RestController
public class WebshopController {

	@Autowired
	protected WebshopService webshopService;

	public WebshopController(WebshopService webshopService) {
		this.webshopService = webshopService;
	}
	
	@RequestMapping("/users/{username}")
	public User findByUsername(Model model, @PathVariable("username") String username) {
		User user = webshopService.findByUsername(username);
		model.addAttribute("user", user);
		return user;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return webshopService.registerUser(user);
	}
	
	@RequestMapping(value = "/categories/create", method = RequestMethod.POST)
	public ResponseEntity<Category> createCategory(@RequestBody String categoryName) {
		return webshopService.addCategory(categoryName);
	}
	
	@HystrixCommand(fallbackMethod = "defaultGetAllCategory")
	@RequestMapping("/categories")
	public ResponseEntity<Category[]> getCategories() {
		return webshopService.getCategories();
	}
	
	public ResponseEntity<Category[]> defaultGetAllCategory() {
		return new ResponseEntity<Category[]>(new Category[]{}, HttpStatus.OK);
	}
	
	@HystrixCommand(fallbackMethod = "defaultDeleteCategory")
	@RequestMapping(value = "categories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", String.valueOf(id));
		return webshopService.deleteCategory(params);
	}
	
	public ResponseEntity<String> defaultDeleteCategory(int id) {
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping("/categories/{name}")
	public ResponseEntity<Category> getCategory(@PathVariable("name") String categoryName) {
		return webshopService.getCategory(categoryName);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return webshopService.createProduct(product);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
		return webshopService.deleteProduct(id);
	}
	
	@RequestMapping(value = "products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
		return webshopService.getProduct(id);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseEntity<Product[]> getProducts() {
		return webshopService.getProducts();
	}
	
	@RequestMapping(value = "/products/{details}/{minPrice}/{maxPrice}", method = RequestMethod.GET)
	public ResponseEntity<Product[]> searchForProducts(@PathVariable("details") String details,
													   @PathVariable("minPrice") String minPrice,
													   @PathVariable("maxPrice") String maxPrice) {
		System.out.println("Details: " + details);
		Map<String, String> params = new HashMap<String, String>();
		params.put("details", details);
		params.put("minPrice", minPrice);
		params.put("maxPrice", maxPrice);
		return webshopService.searchForProducts(params);
	}
}
