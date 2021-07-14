package com.bitinterativo.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitinterativo.fitness.model.PersonalTraining;
import com.bitinterativo.fitness.repository.PersonalTrainingRepository;

@Controller
public class AppController {
	
	@Autowired
	private PersonalTrainingRepository personalTrainingRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/home")
	public ModelAndView home() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		PersonalTraining personalTraining = null;
	
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		ModelAndView modelAndView = new ModelAndView("page/home");
		
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
}
