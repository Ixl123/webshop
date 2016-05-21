package hska.iwi.eShopMaster.microservices.services.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class WebshopController {

	@Autowired
	protected WebshopService webshopService;

	public WebshopController(WebshopService webshopService) {
		this.webshopService = webshopService;
	}
}
