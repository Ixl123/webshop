package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.microservices.webshop.WebshopServer;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListAllProductsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -94109228677381902L;
	
	User user;
	private List<Product> products;
	
	public String execute() throws Exception{
		String result = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		
		if(user != null){
			String serviceUrl = WebshopServer.WEBSHOP_SERVICE_URL + "/products";
			RestTemplate rest = new RestTemplate();
			ResponseEntity<Product[]> response = rest.getForEntity(serviceUrl, Product[].class);
			
			if (response.getStatusCode() == HttpStatus.OK) {
				this.products = Arrays.asList(response.getBody());
				result = "success";
			}
			
//			ProductManager productManager = new ProductManagerImpl();
//			this.products = productManager.getProducts();
//			result = "success";
		}
		
		return result;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
