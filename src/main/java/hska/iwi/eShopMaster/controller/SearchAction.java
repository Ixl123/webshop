package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.microservices.webshop.WebshopServer;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6565401833074694229L;
	
	
	private String searchDescription = null;
	private String searchMinPrice;
	private String searchMaxPrice;
	
	private Double sMinPrice = null;
	private Double sMaxPrice = null;
	
	private User user;
	private List<Product> products;
	private List<Category> categories;
	

	public String execute() throws Exception {
		
		String result = "input";
		
		// Get user:
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		ActionContext.getContext().setLocale(Locale.US);  
		
		if(user != null){
			// Search products and show results:
//			ProductManager productManager = new ProductManagerImpl();
//			this.products = productManager.getProductsForSearchValues(this.searchDescription, this.searchMinPrice, this.searchMaxPrice);
//			if (!searchMinPrice.isEmpty()){
//				sMinPrice =  Double.parseDouble(this.searchMinPrice);
//			}
//			if (!searchMaxPrice.isEmpty()){
//				sMaxPrice =  Double.parseDouble(this.searchMaxPrice);
//			}
			
			String serviceUrl = WebshopServer.WEBSHOP_SERVICE_URL + "/products/{details}/{minPrice}/{maxPrice}";
			RestTemplate rest = new RestTemplate();
			Map<String, String> params = new HashMap<String, String>();
			params.put("details", searchDescription);
			params.put("minPrice", searchMinPrice);
			params.put("maxPrice", searchMaxPrice);
			ResponseEntity<Product[]> response = rest.getForEntity(serviceUrl,
																   Product[].class, params);
			if (response.getStatusCode() == HttpStatus.OK) {
				this.products = Arrays.asList(response.getBody());
			}
//			this.products = productManager.getProductsForSearchValues(this.searchDescription, sMinPrice, sMaxPrice);
			
			// Show all categories:
//			CategoryManager categoryManager = new CategoryManagerImpl();
//			this.categories = categoryManager.getCategories();
			
			serviceUrl = WebshopServer.WEBSHOP_SERVICE_URL + "/categories";
			ResponseEntity<Category[]> categories = rest.getForEntity(serviceUrl, Category[].class);
			if (categories.getStatusCode() == HttpStatus.OK) {
				List<Category> body = Arrays.asList(categories.getBody());
				if (!body.isEmpty()) {
					this.categories = body;
				} else {
					System.out.println("Error: List of categories is empty!");
				}
			} else {
				System.out.println("Error: Could not get categories!");
			}
			result = "success";
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
		
		public List<Category> getCategories() {
			return categories;
		}

		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}
		
		


	public String getSearchValue() {
		return searchDescription;
	}


	public void setSearchValue(String searchValue) {
		this.searchDescription = searchValue;
	}


	public String getSearchMinPrice() {
		return searchMinPrice;
	}


	public void setSearchMinPrice(String searchMinPrice) {
		this.searchMinPrice = searchMinPrice;
	}


	public String getSearchMaxPrice() {
		return searchMaxPrice;
	}


	public void setSearchMaxPrice(String searchMaxPrice) {
		this.searchMaxPrice = searchMaxPrice;
	}


//	public Double getSearchMinPrice() {
//		return searchMinPrice;
//	}
//
//
//	public void setSearchMinPrice(Double searchMinPrice) {
//		this.searchMinPrice = searchMinPrice;
//	}
//
//
//	public Double getSearchMaxPrice() {
//		return searchMaxPrice;
//	}
//
//
//	public void setSearchMaxPrice(Double searchMaxPrice) {
//		this.searchMaxPrice = searchMaxPrice;
//	}
}
