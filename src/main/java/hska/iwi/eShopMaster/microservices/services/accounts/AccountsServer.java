package hska.iwi.eShopMaster.microservices.services.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import hska.iwi.eShopMaster.microservices.accounts.AccountsController;


@EnableAutoConfiguration
@EnableDiscoveryClient
public class AccountsServer {
	
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "accounts-server");
        SpringApplication.run(AccountsServer.class, args);
	}
	
	@Bean
	public AccountsController accountsController() {
		return new AccountsController();
	}

}
