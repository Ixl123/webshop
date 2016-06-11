package hska.iwi.eShopMaster.microservices.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class WebshopServer {

	public static final String WEBSHOP_SERVICE_URL = "http://localhost:3333";
	public static final String USERS_SERVICE_URL = "http://USERS-SERVICE";
	public static final String CATEGORIES_SERVICE_URL = "http://CATEGORIES-SERVICE";
	public static final String PRODUCT_SERVICE_URL = "http://PRODUCTS-SERVICE";

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "webshop-server");
		SpringApplication.run(WebshopServer.class, args);
	}
	
	@Bean
	public WebshopService webshopService() {
		return new WebshopService(USERS_SERVICE_URL,
								  CATEGORIES_SERVICE_URL,
								  PRODUCT_SERVICE_URL);
	}

	@Bean
	public WebshopController accountsController() {
		return new WebshopController(webshopService());
	}
}
