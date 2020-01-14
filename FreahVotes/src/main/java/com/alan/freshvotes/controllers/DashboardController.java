package com.alan.freshvotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String rootView() {
		//changed from index and login should be the first page
		return "login";
//		TEST
	}

}
