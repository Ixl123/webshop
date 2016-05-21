package hska.iwi.eShopMaster.microservices.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hska.iwi.eShopMaster.model.database.dataobjects.User;

@RestController
public class UsersController {

	protected UserRepository userRepository;
	
	@Autowired
	public UsersController(UserRepository userRepository) {
		this.userRepository = userRepository;
		System.out.println("----- Users: " + userRepository.countUsers());
	}
	
	@RequestMapping("/users/{username}")
	public User byNumber(@PathVariable("username") String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}
}
