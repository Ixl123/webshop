package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.microservices.products.domain.Product;
import hska.iwi.eShopMaster.microservices.webshop.WebshopServer;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.ProductDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteProductAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666796923937616729L;

	private int id;

	public String execute() throws Exception {
		
		String res = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("webshop_user");
		
		if(user != null && (user.getRole().getTyp().equals("admin"))) {

			String serviceUrl = WebshopServer.WEBSHOP_SERVICE_URL + "/products/{id}";
			RestTemplate rest = new RestTemplate();
			rest.delete(serviceUrl, id);
			
			ResponseEntity<Product> response = rest.getForEntity(serviceUrl, Product.class, id);
			
			if ((response.getStatusCode() == HttpStatus.OK) && (response.getBody() != null)) {
				res = "success";
			}
			
//			new ProductDAO().deleteById(id);
//			{
//				res = "success";
//			}
		}
		
		return res;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
