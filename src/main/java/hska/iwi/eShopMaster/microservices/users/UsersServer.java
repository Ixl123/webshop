package hska.iwi.eShopMaster.microservices.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import hska.iwi.eShopMaster.microservices.users.DBUsersConfiguration;
import hska.iwi.eShopMaster.microservices.users.domain.RoleRepository;
import hska.iwi.eShopMaster.microservices.users.domain.UserRepository;


@SpringBootApplication
@EnableDiscoveryClient
@Import(DBUsersConfiguration.class)
public class UsersServer {
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected RoleRepository roleRepository;
	
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "users-server");
        SpringApplication.run(UsersServer.class, args);
	}	
}
