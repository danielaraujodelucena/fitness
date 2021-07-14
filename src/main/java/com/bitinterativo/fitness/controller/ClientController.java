package com.bitinterativo.fitness.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitinterativo.fitness.model.Client;
import com.bitinterativo.fitness.model.PersonalTraining;
import com.bitinterativo.fitness.report.ReportUtil;
import com.bitinterativo.fitness.repository.ClientRepository;
import com.bitinterativo.fitness.repository.PersonalTrainingRepository;

@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PersonalTrainingRepository personalTrainingRepository;
	
	@Autowired
	private ReportUtil reportUtil;
	
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
	
	@RequestMapping(method=RequestMethod.POST, value="/generatePdfAllClients")
	public void generatePdfAllClients(@RequestParam("idPersonalTraining") String id, HttpServletRequest request, HttpServletResponse response) {
		List<Client> clients = clientRepository.findClientByIdPersonalTraining(Long.parseLong(id));
		
		try {
			byte[] pdf = reportUtil.gerarRelatorio(clients, "clients", request.getServletContext());
			
			response.setContentLength(pdf.length);
			response.setContentType("application/octet-stream");
			
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", "clients.pdf");
			
			response.setHeader(headerKey, headerValue);
			
			response.getOutputStream().write(pdf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/7-dobras")
	public ModelAndView Dobras7() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
		
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		Iterable<Client> clientIt = clientRepository.findClientByIdPersonalTraining(personalTraining.getId());
		
		ModelAndView modelAndView = new ModelAndView("page/7-dobras");
		modelAndView.addObject("clientList", clientIt);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
}
