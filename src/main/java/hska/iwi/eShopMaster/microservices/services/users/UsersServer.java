package hska.iwi.eShopMaster.microservices.services.users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import hska.iwi.eShopMaster.microservices.users.UserRepository;


@EnableAutoConfiguration
//@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("hska.iwi.eShopMaster.microservices.users")
@EnableJpaRepositories("hska.iwi.eShopMaster.microservices.users")
@PropertySource("classpath:db-config.properties")
public class UsersServer {
	
	@Autowired
	protected UserRepository userRepository;
	
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "users-server");
        SpringApplication.run(UsersServer.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/webshop");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
		
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
