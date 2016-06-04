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

public class AddCategoryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6704600867133294378L;
	
	private String newCatName = null;
	private List<Category> categories;
	private User user;
	private String serviceUrl = WebshopServer.WEBSHOP_SERVICE_URL + "/categories/create";
	private String serviceUrlGet = WebshopServer.WEBSHOP_SERVICE_URL + "/categories";

	public String execute() throws Exception {

		String res = "input";

		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		if(user != null && (user.getRole().getTyp().equals("admin"))) {
			RestTemplate rest = new RestTemplate();
			ResponseEntity<Category> response = rest.postForEntity(serviceUrl,
																   newCatName,
																   Category.class);

			if (response.getStatusCode() == HttpStatus.CREATED) {
				System.out.println("Successfully Category created!");
				ResponseEntity<Category[]> categories = rest.getForEntity(serviceUrlGet, Category[].class);
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
			} else {
				System.out.println("Error: User could not be created!");
			}
			
//			CategoryManager categoryManager = new CategoryManagerImpl();
//			// Add category
//			categoryManager.addCategory(newCatName);
//			
//			// Go and get new Category list
//			this.setCategories(categoryManager.getCategories());
			
			res = "success";
		}
		return res;
	}
	
	@Override
	public void validate(){
		if (getNewCatName().length() == 0) {
			addActionError(getText("error.catname.required"));
		}
		// Go and get new Category list
//		CategoryManager categoryManager = new CategoryManagerImpl();
//		this.setCategories(categoryManager.getCategories());
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Category[]> categories = rest.getForEntity(serviceUrlGet, Category[].class);
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
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public String getNewCatName() {
		return newCatName;
	}

	public void setNewCatName(String newCatName) {
		this.newCatName = newCatName;
	}
}
