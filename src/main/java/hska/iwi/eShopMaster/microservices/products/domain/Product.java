package hska.iwi.eShopMaster.microservices.products.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hska.iwi.eShopMaster.microservices.categories.domain.Category;

/**
 * This class contains details about products.
 */
@Entity
@Table(name = "product")
public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private double price;

	@Column(name = "category_id")
	private int category_id;

	@Column(name = "details")
	private String details;

	public Product() {
	}

	public Product(String name, double price, int category_id) {
		this.name = name;
		this.price = price;
		this.category_id = category_id;
	}

	public Product(String name, double price, int category_id, String details) {
		this.name = name;
		this.price = price;
		this.category_id = category_id;
		this.details = details;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return this.category_id;
	}

	public void setCategoryId(int category_id) {
		this.category_id = category_id;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
