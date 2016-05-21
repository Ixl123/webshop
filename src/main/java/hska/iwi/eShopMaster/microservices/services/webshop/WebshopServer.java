package hska.iwi.eShopMaster.microservices.services.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class WebshopServer {

	public static final String USERS_SERVICE_URL = "http://USERS-SERVICE";

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "webshop-server");
		SpringApplication.run(WebshopServer.class, args);
	}
	
	@Bean
	public WebshopService webshopService() {
		return new WebshopService(USERS_SERVICE_URL);
	}

	@Bean
	public WebshopController accountsController() {
		return new WebshopController(webshopService());
	}
}
