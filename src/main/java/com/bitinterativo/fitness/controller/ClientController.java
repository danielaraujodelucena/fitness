package com.bitinterativo.fitness.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitinterativo.fitness.model.Client;
import com.bitinterativo.fitness.repository.ClientRepository;

@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/client")
	public ModelAndView inicio() {
		Iterable<Client> clientIt = clientRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("page/client");
		modelAndView.addObject("clientList", clientIt);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/save-client")
	public ModelAndView save(Client client) {
		clientRepository.save(client);
		
		ModelAndView modelAndView = new ModelAndView("page/client");
		Iterable<Client> clientIt = clientRepository.findAll();
		modelAndView.addObject("clientList", clientIt);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/list-client")
	public ModelAndView findAll() {
		Iterable<Client> clientIt = clientRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("page/client");
		modelAndView.addObject("clientList", clientIt);
		
		return modelAndView;
	}
	
	@GetMapping("/client-edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Optional<Client> client = clientRepository.findById(id);
		
		ModelAndView modelAndView = new ModelAndView("page/client-edit");
		modelAndView.addObject("client", client.get());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/client-delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		clientRepository.deleteById(id);
		
		return findAll();
	}
}
