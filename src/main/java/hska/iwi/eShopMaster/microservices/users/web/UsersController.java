package hska.iwi.eShopMaster.microservices.users.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hska.iwi.eShopMaster.microservices.users.domain.RoleRepository;
import hska.iwi.eShopMaster.microservices.users.domain.UserRepository;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

@RestController
public class UsersController {

	protected UserRepository userRepository;
	protected RoleRepository roleRepository;
	
	@Autowired
	public UsersController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	public User findByUsername(@PathVariable("username") String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		user.setRole(roleRepository.findByLevel(1));
		User createdUser = userRepository.save(user);
		return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
	}
}
