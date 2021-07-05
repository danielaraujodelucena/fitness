package com.bitinterativo.fitness.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView inicio() {
		Iterable<PersonalTraining> personalIt = personalTrainingRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("page/personal-training");
		modelAndView.addObject("personalTrainingList", personalIt);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/save-personal-training")
	public ModelAndView save(PersonalTraining personalTraining) {
		personalTrainingRepository.save(personalTraining);
		
		ModelAndView modelAndView = new ModelAndView("page/personal-training");
		Iterable<PersonalTraining> personalIt = personalTrainingRepository.findAll();
		modelAndView.addObject("personalTrainingList", personalIt);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/list-personal-training")
	public ModelAndView findAll() {
		Iterable<PersonalTraining> personalIt = personalTrainingRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("page/personal-training");
		modelAndView.addObject("personalTrainingList", personalIt);
		
		return modelAndView;
	}
	
	@GetMapping("/personal-training-edit/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<PersonalTraining> personalTraining = personalTrainingRepository.findById(id);
		
		ModelAndView modelAndView = new ModelAndView("page/personal-training-edit");
		modelAndView.addObject("personalTraining", personalTraining.get());
		
		return modelAndView;
	}
}
