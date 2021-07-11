package com.bitinterativo.fitness.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitinterativo.fitness.model.Client;
import com.bitinterativo.fitness.model.PersonalTraining;
import com.bitinterativo.fitness.repository.ClientRepository;
import com.bitinterativo.fitness.repository.PersonalTrainingRepository;

@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PersonalTrainingRepository personalTrainingRepository;
	
	//@Autowired
	//private PersonRoleRepository personRoleRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/client")
	public ModelAndView inicio() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
	
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		Iterable<Client> clientIt = clientRepository.findClientByIdPersonalTraining(personalTraining.getId());
		
		ModelAndView modelAndView = new ModelAndView("page/client");
		modelAndView.addObject("clientList", clientIt);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/save-client")
	public ModelAndView save(Client client) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
		
		Client clientSave = clientRepository.save(client);
		
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		//PersonRole personRole = new PersonRole(clientSave.getId(), 3L);
		//personRoleRepository.save(personRole);
		
		clientRepository.savePersonRole(clientSave.getId(), 3L);
		
		Iterable<Client> clientIt = clientRepository.findClientByIdPersonalTraining(personalTraining.getId());
		
		ModelAndView modelAndView = new ModelAndView("page/client");
		modelAndView.addObject("clientList", clientIt);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/list-client")
	public ModelAndView findAll() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
		
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		Iterable<Client> clientIt = clientRepository.findClientByIdPersonalTraining(personalTraining.getId());
		
		ModelAndView modelAndView = new ModelAndView("page/client");
		modelAndView.addObject("clientList", clientIt);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
	
	@GetMapping("/client-edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
		
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		Optional<Client> client = clientRepository.findById(id);
		
		ModelAndView modelAndView = new ModelAndView("page/client-edit");
		modelAndView.addObject("client", client.get());
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/client-delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		clientRepository.deleteById(id);
		clientRepository.deletePersonRole(id);
		
		return findAll();
	}
}
