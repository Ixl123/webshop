package hska.iwi.eShopMaster.microservices.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

	protected UserRepository userRepository;
	
	@Autowired
	public UsersController(UserRepository userRepository) {
		this.userRepository = userRepository;
		System.out.println("----- Users: " + userRepository.countUsers());
	}
}
