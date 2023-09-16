package com.tutorial.springsecurity.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	@GetMapping("/accessDenied")
	public String showErrorPage() {
		return "access-denied";
	}
	
	@GetMapping("/loginPage")
	public String showLogin() {
		return "login-page";
	}
	
}
