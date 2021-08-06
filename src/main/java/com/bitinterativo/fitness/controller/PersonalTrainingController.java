package com.bitinterativo.fitness.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
	
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		Iterable<PersonalTraining> personalIt = personalTrainingRepository.findAllPersonalTraining();
		
		ModelAndView modelAndView = new ModelAndView("page/personal-training");
		modelAndView.addObject("personalTrainingList", personalIt);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/save-personal-training")
	public ModelAndView save(PersonalTraining personalTraining) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		PersonalTraining personal = null;
		String passwordSecurity = null;
		
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personal = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		passwordSecurity = enconder.encode(personalTraining.getPassword());
		personalTraining.setPassword(passwordSecurity);
		
		PersonalTraining personalSave = personalTrainingRepository.save(personalTraining);
		
		personalTrainingRepository.savePersonRole(personalSave.getId(), 2L);
		
		Iterable<PersonalTraining> personalIt = personalTrainingRepository.findAllPersonalTraining();
		
		ModelAndView modelAndView = new ModelAndView("page/personal-training");
		modelAndView.addObject("personalTrainingList", personalIt);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personal);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/list-personal-training")
	public ModelAndView findAll() {
		Iterable<PersonalTraining> personalIt = personalTrainingRepository.findAllPersonalTraining();
		
		ModelAndView modelAndView = new ModelAndView("page/personal-training");
		modelAndView.addObject("personalTrainingList", personalIt);
		
		return modelAndView;
	}
	
	@GetMapping("/personal-training-edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personal = null;
		
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personal = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		Optional<PersonalTraining> personalTraining = personalTrainingRepository.findById(id);
		
		ModelAndView modelAndView = new ModelAndView("page/personal-training-edit");
		modelAndView.addObject("personalTraining", personalTraining.get());
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personal);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/personal-training-delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		personalTrainingRepository.deleteById(id);
		personalTrainingRepository.deletePersonRole(id);
		
		return inicio();
	}
	
	@GetMapping("/reset-password-personal-training/{id}")
	public ModelAndView resetPasswword(@PathVariable("id") Long id) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		String password = null;
		PersonalTraining person = null;
		Optional<PersonalTraining> personalTrainingOpt = null;
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			person = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		if(personalTrainingRepository.findById(id) != null) {
			personalTrainingOpt = personalTrainingRepository.findById(id);
		}
		
		password = enconder.encode(personalTrainingOpt.get().getUsername());
		personalTrainingOpt.get().setPassword(password);
		personalTrainingRepository.save(personalTrainingOpt.get());
		
		ModelAndView modelAndView = new ModelAndView("page/personal-training-edit");
		modelAndView.addObject("personalTraining", personalTrainingOpt.get());
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", person);
		
		return modelAndView;
	}
}
