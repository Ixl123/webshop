package hska.iwi.eShopMaster.microservices.products.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hska.iwi.eShopMaster.microservices.products.domain.Product;
import hska.iwi.eShopMaster.microservices.products.domain.ProductRepository;

@RestController
public class ProductController {

	protected ProductRepository productRepository;
	
	@Autowired
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		productRepository.save(product);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
		productRepository.delete(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/byCategory/{categoryid}", method = RequestMethod.GET)
	public List<Product> getProductsWithCategoryId(@PathVariable("categoryid") int categoryid) {
		List<Product> list = productRepository.getProductsWithCategoryId(categoryid);
		return list;
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
		Product product = productRepository.findOne(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getProducts() {
		List<Product> products = (List<Product>) productRepository.findAll();
		return products;
	}
	
	@RequestMapping(value = "/products/{details}/{minPrice}/{maxPrice}", method = RequestMethod.GET)
	public List<Product> searchForProducts(@PathVariable("details") String details,
										   @PathVariable("minPrice") String minPrice,
										   @PathVariable("maxPrice") String maxPrice) {
		System.out.println("Product service is searching");
		Double minPrice1 = Double.parseDouble(minPrice);
		Double maxPrice1 = Double.parseDouble(maxPrice);
		System.out.println("Params: " + details + " " + minPrice + " " + maxPrice);
		List<Product> products = productRepository.searchForProducts(details,
																	 minPrice1,
																	 maxPrice1);
		System.out.println("Count: " + products.size());
		return products;
	}
	
	@RequestMapping(value = "products/{id}/categoryid", method = RequestMethod.GET)
	public ResponseEntity<Integer> getCategoryIdFromProduct(@PathVariable("id") int id) {
		int categoryId = productRepository.findOne(id).getCategoryId();
		return new ResponseEntity<Integer>(categoryId, HttpStatus.OK);
	}
}
