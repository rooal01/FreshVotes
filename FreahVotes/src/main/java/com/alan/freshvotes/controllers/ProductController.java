package com.alan.freshvotes.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.alan.freshvotes.domain.Product;
import com.alan.freshvotes.domain.User;
import com.alan.freshvotes.repositories.ProductRepository;

import javassist.NotFoundException;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;

	//moving the following into dashboard so no need for the products.html page
//	@GetMapping("/products")
//	public String getProducts(@AuthenticationPrincipal User user,ModelMap model) {
//		
//	List<Product> products = productRepo.findByUser(user);
//	model.put("products",  products);
//	
//		return "products";
//	}
	
	@GetMapping("/products/{productId}")
	public String getProduct(@PathVariable Long productId, ModelMap model, HttpServletResponse response) throws NotFoundException, IOException {
		
		//Bring in the model so we can place the product onto it so it can be used by the next view
		//Bring in HttpServletResponse so we can control the response when the product id is not found.
		//Optional is new in Java 8 it tries to prevent null point exceptions
		//Note in the following Optional is a wrapper around the product
		Optional<Product> productOpt = productRepo.findByIdWithUser(productId);
		
		//so to get teh rpoduct we
		
		if (productOpt.isPresent()) {
			
			Product product = productOpt.get();
			model.put("product", product);
		} else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "Product Not Found");
			return "product";
		}
		
		
				
		return "product";
	}
	
	@PostMapping("/products/{productId}")
	public String saveProduct(@AuthenticationPrincipal User user, @PathVariable Long productId, Product product) {
		
		//added to ensure the user_id is set correctly. If not set the user_id will changed to null in the table which is not correct.
		//this is because the product model returned by the post does not have the user object populated.
		//product.setUser(user);
		//I should be able to Disabled the above because the root of the issue was that the user was not getting populated by the findById
		//The table is setup to use Lazy so. We have to craete a new query to ensure the user id gets populated with the product.
		//CHECK FINAL CODE TO SEE WHY THIS DOES NOT WORK
		//I finally updated the product.html and user product.user.id instead of product.user
		
		System.out.println("this is a test");
		productRepo.save(product);

		
		return "redirect:/products/"+product.getId();
		
	}
	
	@PostMapping("/products")
	public String createProduct(@AuthenticationPrincipal User user) {
		//@AuthenticationPrincipal is used to map in the current user

		Product product = new Product();
		product.setPublished(false);
		
		//Note sometimes, maybe spring version specific, the @AuthenticationPrincipal is not tided to the hibernate user correctly
		//so you need to do a getuser using the id from the principal and then save the user
		//so something like the following. You can also autowire the userrep
		//		UserRepository urepo = new UserRepository();
		//		urepo.findById(user.getId());

		
		product.setUser(user);
		

		product = productRepo.save(product);
		
		
		//this will get caught by the get above and return the product page
		return "redirect:products/"+product.getId();
	}

}
