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
import com.bitinterativo.fitness.model.PhysicalTestPerson;
import com.bitinterativo.fitness.model.Pollock7Fold;
import com.bitinterativo.fitness.repository.ClientRepository;
import com.bitinterativo.fitness.repository.PersonalTrainingRepository;
import com.bitinterativo.fitness.repository.PhysicalTestPersonRepository;
import com.bitinterativo.fitness.repository.Pollock7FoldRepository;

@Controller
public class Pollock7FoldController {
	
	@Autowired
	private Pollock7FoldRepository pollock7FoldRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PersonalTrainingRepository personalTrainingRepository;
	
	@Autowired
	private PhysicalTestPersonRepository physicalTestPersonRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/pollock7fold")
	public ModelAndView inicio() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
	
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		Iterable<Client> clientIt = clientRepository.findClientByIdPersonalTraining(personalTraining.getId());
	
		
		Iterable<PhysicalTestPerson> physicalTestPersonIt = physicalTestPersonRepository.findAll2("Pollock 7", personalTraining.getId());
		
		ModelAndView modelAndView = new ModelAndView("page/pollock7fold");
		modelAndView.addObject("listClient", clientIt);
		modelAndView.addObject("listPhysicalTestPerson", physicalTestPersonIt);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
														 
	@RequestMapping(method=RequestMethod.POST, value="**/save-pollock7fold")
	public ModelAndView save(Pollock7Fold pollock7Fold) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
		
		pollock7FoldRepository.save(pollock7Fold);
		
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		Iterable<Client> clientIt = clientRepository.findClientByIdPersonalTraining(personalTraining.getId());
		Iterable<PhysicalTestPerson> physicalTestPersonIt = physicalTestPersonRepository.findAll2("Pollock 7", personalTraining.getId());
		
		ModelAndView modelAndView = new ModelAndView("page/pollock7fold");
		modelAndView.addObject("listClient", clientIt);
		modelAndView.addObject("listPhysicalTestPerson", physicalTestPersonIt);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
	
	@GetMapping("/pollock7fold-edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
		
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		Optional<Pollock7Fold> pollock7fold = pollock7FoldRepository.findById(id);
		
		Client client = clientRepository.findByClientId(pollock7fold.get().getId_client());
		
		ModelAndView modelAndView = new ModelAndView("page/pollock7fold-edit");
		modelAndView.addObject("client", client);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		modelAndView.addObject("pollock7fold", pollock7fold);
		
		return modelAndView;
	}
}
