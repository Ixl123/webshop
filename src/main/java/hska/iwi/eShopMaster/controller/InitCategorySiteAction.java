package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.microservices.webshop.WebshopServer;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InitCategorySiteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1108136421569378914L;

	private String pageToGoTo;
	private User user;

	private List<Category> categories;

	public String execute() throws Exception {
		
		String res = "input";

		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		boolean isAdmin = true;
		if(user != null && isAdmin) {
			String serviceUrl = WebshopServer.WEBSHOP_SERVICE_URL + "/categories";
			RestTemplate rest = new RestTemplate();
			ResponseEntity<Category[]> categories = rest.getForEntity(serviceUrl, Category[].class);
			if (categories.getStatusCode() == HttpStatus.OK) {
				List<Category> body = Arrays.asList(categories.getBody());
				if (!body.isEmpty()) {
					this.setCategories(body);
				} else {
					System.out.println("Error: List of categories is empty!");
				}
			} else {
				System.out.println("Error: Could not get categories!");
			}
			
			if(pageToGoTo != null){
				if(pageToGoTo.equals("c")){
					res = "successC";	
				}
				else if(pageToGoTo.equals("p")){
					res = "successP";
				}				
			}
		}
		
		return res;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getPageToGoTo() {
		return pageToGoTo;
	}

	public void setPageToGoTo(String pageToGoTo) {
		this.pageToGoTo = pageToGoTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
