package com.bitinterativo.fitness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("page/home");
		
		return modelAndView;
	}
}
