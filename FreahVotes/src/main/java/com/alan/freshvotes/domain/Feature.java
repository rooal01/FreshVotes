package com.alan.freshvotes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Feature {
	

	private Integer id;
	private String title;
	private String description;
	private String status;
	private Product product;
	private User user;
	
	
	//This means that a feature maps to only one product, byt a product can have many features. On the product entity side this will be a OneToMany setting and the product side will control the cascade setting, what happens if the product is removed.
	@ManyToOne
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne //So a user can have many features. in the user entity it is defined as a one to many because a user can have many features.
	//The user side of this relationship controls the cascade setting (what happens if the user is deleted)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
