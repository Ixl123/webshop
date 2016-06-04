package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.microservices.webshop.WebshopServer;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCategoryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1254575994729199914L;
	
	private int catId;
	private List<Category> categories;

	public String execute() throws Exception {
		
		String res = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("webshop_user");
		
		if(user != null && (user.getRole().getTyp().equals("admin"))) {

			Map<String, String> params = new HashMap<String, String>();
			params.put("id", String.valueOf(catId));

			String serviceUrl = WebshopServer.WEBSHOP_SERVICE_URL + "/categories/{id}";
			RestTemplate rest = new RestTemplate();
			rest.delete(serviceUrl, params);

			serviceUrl = WebshopServer.WEBSHOP_SERVICE_URL + "/categories";
			ResponseEntity<Category[]> categories = rest.getForEntity(serviceUrl,
																	  Category[].class);
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
			
			// Helper inserts new Category in DB:
//			CategoryManager categoryManager = new CategoryManagerImpl();
//		
//			categoryManager.delCategoryById(catId);
//
//			categories = categoryManager.getCategories();
				
			res = "success";

		}
		
		return res;
		
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
