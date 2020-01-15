package com.alan.freshvotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alan.freshvotes.domain.User;
import com.alan.freshvotes.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
//	You can also just use @GetMapping("/login")
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginView() {
		return "login";
}


	@RequestMapping(value="/register", method=RequestMethod.GET)
	//see how we return a modelmap with a user model so it can be populated by the register page
	public String Register(ModelMap model) {
		model.put("user",  new User());
		return "register";

}
	//COULD ALSO JUST USE @POSTMAPPING(value="/register")
	@RequestMapping(value="/register", method=RequestMethod.POST)
	//the registration page will return a user object with the users input parameters.
	//not sure you need to have the @ModelAttribute any more
	public String RegisterPost(@ModelAttribute User user) {
		System.out.println(user);
		User saveduser = userService.save(user);
		System.out.println(saveduser);
		return "redirect:login";

}
	
//	added this to redirect to homepage after successful login. I also needed to update the websecurityconfiguration to ensure the redirect worked. You cannot simple use the action value in html when using spring security
	@RequestMapping(value="/dashboard", method=RequestMethod.POST)
	public String homePage() {
		return "redirect:dashboard";

}
}
