package hska.iwi.eShopMaster.microservices.products.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product createdProduct = productRepository.save(product);
		System.out.println(createdProduct.getName());
		return new ResponseEntity<Product>(createdProduct, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
		productRepository.delete(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping("/products/byCategory/{categoryid}")
	public List<Product> getProductsWithCategoryId(@PathVariable("categoryid") int categoryid) {
		List<Product> list = productRepository.getProductsWithCategoryId(categoryid);
		return list;
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
		Product product = productRepository.findOne(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK); 
	}
	
	@RequestMapping()
	public List<Product> getProducts() {
		List<Product> products = (List<Product>) productRepository.findAll();
		return products;
	}
}
