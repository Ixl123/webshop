package hska.iwi.eShopMaster.microservices.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import hska.iwi.eShopMaster.microservices.products.domain.ProductRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsServer {
	
	@Autowired
	protected ProductRepository productRepository;

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "products-server");
        SpringApplication.run(ProductsServer.class, args);
	}
}
