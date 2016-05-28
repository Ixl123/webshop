package hska.iwi.eShopMaster.microservices.services.webshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

@Service
public class WebshopService {

	@Autowired
	protected RestTemplate restTemplate;

	protected String serviceUrl;
	
	public WebshopService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	public User findByUsername(String username) {
		return restTemplate.getForObject(serviceUrl + "/users/{username}",
				User.class, username);
	}
	
	public ResponseEntity<User> registerUser(User user) {
		return restTemplate.postForEntity(serviceUrl + "/users", user, User.class);
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