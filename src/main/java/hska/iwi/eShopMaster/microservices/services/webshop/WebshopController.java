package hska.iwi.eShopMaster.microservices.services.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hska.iwi.eShopMaster.model.database.dataobjects.User;

@Controller
public class WebshopController {

	@Autowired
	protected WebshopService webshopService;

	public WebshopController(WebshopService webshopService) {
		this.webshopService = webshopService;
	}
	
	@RequestMapping("/users/{username}")
	@ResponseBody
	public String byNumber(Model model, @PathVariable("username") String username) {

		User user = webshopService.findByUsername(username);
		model.addAttribute("user", user);
		
		return user.getFirstname() + " " + user.getLastname();
	}
}
