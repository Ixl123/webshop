package hska.iwi.eShopMaster.microservices.products;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import hska.iwi.eShopMaster.microservices.products.domain.ProductRepository;
import hska.iwi.eShopMaster.microservices.users.UsersServer;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsServer {
	
	@Autowired
	protected ProductRepository productRepository;

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "products-server");
        SpringApplication.run(ProductsServer.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		
		String filename = "db-config.properties";
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = UsersServer.class.getClassLoader().getResourceAsStream(filename);
            properties.load(input);

            dataSource.setDriverClassName(properties.getProperty("spring.jpa.driver_class"));
            dataSource.setUrl(properties.getProperty("spring.jpa.url"));
            dataSource.setUsername(properties.getProperty("spring.jpa.username"));
            dataSource.setPassword(properties.getProperty("spring.jpa.password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataSource;
	}

}
