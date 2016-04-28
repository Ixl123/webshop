package hska.iwi.eShopMaster.microservices.accounts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountsController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
}
