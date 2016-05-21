package hska.iwi.eShopMaster.microservices.services.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
