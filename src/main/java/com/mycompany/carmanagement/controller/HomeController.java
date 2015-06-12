package com.mycompany.carmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.carmanagement.annotaion.Layout;

@Controller
class HomeController {
	@Layout(value = "blank")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String index() {
		return "home";
	}
}