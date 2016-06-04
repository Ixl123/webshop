package hska.iwi.eShopMaster.microservices.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hska.iwi.eShopMaster.model.database.dataobjects.Category;
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
		System.out.println("POST!");
		return webshopService.addCategory(categoryName);
	}
	
	@RequestMapping("/categories")
	public ResponseEntity<Category[]> getCategories() {
		return webshopService.getCategories();
	}
}
