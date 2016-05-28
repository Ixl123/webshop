package hska.iwi.eShopMaster.microservices.services.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

@Controller
public class WebshopController {

	@Autowired
	protected WebshopService webshopService;

	public WebshopController(WebshopService webshopService) {
		this.webshopService = webshopService;
	}
	
	@RequestMapping("/users/{username}")
	@ResponseBody
	public User findByUsername(Model model, @PathVariable("username") String username) {
		User user = webshopService.findByUsername(username);
		model.addAttribute("user", user);
		return user;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		System.out.println("POST!");
		return webshopService.registerUser(user);
	}
}
