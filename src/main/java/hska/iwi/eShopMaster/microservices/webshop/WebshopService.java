package hska.iwi.eShopMaster.microservices.webshop;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

@Service
public class WebshopService {

	@Autowired
	protected RestTemplate restTemplate;

	protected String userServiceUrl;
	protected String categoryServiceUrl;
	
	public WebshopService(String userServiceUrl, String categoryServiceUrl) {
		this.userServiceUrl = userServiceUrl.startsWith("http") ? userServiceUrl
				: "http://" + userServiceUrl;
		this.categoryServiceUrl = categoryServiceUrl.startsWith("http") ? categoryServiceUrl
				: "http://" + categoryServiceUrl;
	}
	
	public User findByUsername(String username) {
		return restTemplate.getForObject(userServiceUrl + "/users/{username}",
				User.class, username);
	}
	
	public ResponseEntity<User> registerUser(User user) {
		return restTemplate.postForEntity(userServiceUrl + "/users", user, User.class);
	}
	
	public ResponseEntity<Category> addCategory(String categoryName) {
		return restTemplate.postForEntity(categoryServiceUrl + "/categories/create", categoryName, Category.class);
	}
	
	public ResponseEntity<Category[]> getCategories() {
		return restTemplate.getForEntity(categoryServiceUrl + "/categories", Category[].class);
	}
	
	public ResponseEntity<String> deleteCategory(Map<String, String> params) {
		restTemplate.delete(categoryServiceUrl + "/categories/{id}", params);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	public ResponseEntity<Category> getCategory(String categoryName) {
		return restTemplate.getForEntity(categoryServiceUrl + "/categories/{name}",
										 Category.class, categoryName);
	}
}

//@Component
//class ServiceDiscoveryClient {
//	
//	@Autowired
//	private DiscoveryClient discoveryClient;
//	
//	public String serviceInstancesByApplicationName(String applicationName) {
//		List<ServiceInstance> list = this.discoveryClient.getInstances(applicationName);
//		for (ServiceInstance si: list) {
//			System.out.println(si.getUri().toString());
//			System.out.println(si.getHost() + " " + si.getPort());
//			System.out.println(si.getServiceId());
//			return si.getUri().toString();
//		}
//		return "null";
//	}
//}