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
//		TEST

}
}
