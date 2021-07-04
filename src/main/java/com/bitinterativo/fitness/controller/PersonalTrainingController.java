package com.bitinterativo.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitinterativo.fitness.model.PersonalTraining;
import com.bitinterativo.fitness.repository.PersonalTrainingRepository;

@Controller
public class PersonalTrainingController {
	
	@Autowired
	private PersonalTrainingRepository personalTrainingRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/personal-training")
	public String inicio() {
		return "page/personal-training";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/save-personal-training")
	public String save(PersonalTraining personalTraining) {
		personalTrainingRepository.save(personalTraining);
		return "page/personal-training";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/list-personal-training")
	public ModelAndView findAll() {
		ModelAndView modelAndView = new ModelAndView("page/personal-training");
		Iterable<PersonalTraining> personalIt = personalTrainingRepository.findAll();
		modelAndView.addObject("personalTrainingList", personalIt);
		
		return modelAndView;
	}
}
