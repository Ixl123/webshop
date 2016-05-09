package hska.iwi.eShopMaster.microservices.accounts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountsController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public String home() {
		System.out.println("Hello World!");
		return "Hello World!";
	}
	
}
