package com.alan.freshvotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Login {
	
//	You can also just use @GetMapping("/login")
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginView() {
		return "login";

}


	
//	added this to redirect to homepage after successful login. I also needed to update the websecurityconfiguration to ensure the redirect worked. You cannot simple use the action value in html when using spring security
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String homePage() {
		return "homepage";

}
}
