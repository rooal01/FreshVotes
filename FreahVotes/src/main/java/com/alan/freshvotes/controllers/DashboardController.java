package com.alan.freshvotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alan.freshvotes.domain.Product;
import com.alan.freshvotes.domain.User;
import com.alan.freshvotes.repositories.ProductRepository;

@Controller
public class DashboardController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String rootView() {
		//changed from index and login should be the first page
		return "index";
//		TEST
	}
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String dashboard(@AuthenticationPrincipal User user,ModelMap model) {
		

		//List<Product> products = productRepo.findByUser(user);
		//Lets only show published ones and ones the user owns
		//Next we need to protect products not owned by the user on the front and backend.
		List<Product> products = productRepo.findAllByPublishedOrUser(true, user);
		
	
		model.put("products",  products);
		
		return "/dashboard";

	}

}
