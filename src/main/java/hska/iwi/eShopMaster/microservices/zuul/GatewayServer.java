package hska.iwi.eShopMaster.microservices.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GatewayServer {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "gateway-server");
		SpringApplication.run(GatewayServer.class, args);
	}

}
