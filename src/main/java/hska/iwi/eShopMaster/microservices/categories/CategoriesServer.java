package hska.iwi.eShopMaster.microservices.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import hska.iwi.eShopMaster.microservices.categories.domain.CategoryRepository;

@SpringBootApplication
@EnableDiscoveryClient
@Import(DBCategoriesConfiguration.class)
public class CategoriesServer {
	
	@Autowired
	protected CategoryRepository categoryRepository;

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "categories-server");
        SpringApplication.run(CategoriesServer.class, args);
	}

}
