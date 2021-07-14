package com.bitinterativo.fitness.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitinterativo.fitness.model.Client;
import com.bitinterativo.fitness.model.PersonalTraining;
import com.bitinterativo.fitness.model.PhysicalAssessment;
import com.bitinterativo.fitness.model.Pollock7Fold;
import com.bitinterativo.fitness.repository.ClientRepository;
import com.bitinterativo.fitness.repository.PersonalTrainingRepository;
import com.bitinterativo.fitness.repository.PhysicalAssessmentRepository;
import com.bitinterativo.fitness.repository.Pollock7FoldRepository;

@Controller
public class Pollock7FoldController {
	
	@Autowired
	private Pollock7FoldRepository pollock7FoldRepository;
	
	@Autowired
	private PhysicalAssessmentRepository physicalAssessmentRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PersonalTrainingRepository personalTrainingRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/pollock7fold")
	public ModelAndView inicio() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
	
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		Iterable<Client> clientIt = clientRepository.findClientByIdPersonalTraining(personalTraining.getId());
	
		ModelAndView modelAndView = new ModelAndView("page/pollock7fold");
		modelAndView.addObject("listClient", clientIt);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
														 
	@RequestMapping(method=RequestMethod.POST, value="**/save-pollock7fold")
	public ModelAndView save(Pollock7Fold pollock7Fold) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalTraining personalTraining = null;
		
		Pollock7Fold pollock7FoldSave = pollock7FoldRepository.save(pollock7Fold);
		
		if(personalTrainingRepository.findPersonByUserName(userName) != null) {
			personalTraining = personalTrainingRepository.findPersonByUserName(userName);
		}
		
		// tem que criar e salvar o physical assessment (date, client, personalTraining, pollock)
		
		Optional<Client> client = clientRepository.findById(pollock7Fold.getIdClient());
		
		PhysicalAssessment physicalAssessment = new PhysicalAssessment(new Date(), client.get(), personalTraining, pollock7FoldSave.getId());
		physicalAssessmentRepository.save(physicalAssessment);
		
		Iterable<Client> clientIt = clientRepository.findClientByIdPersonalTraining(personalTraining.getId());
		
		ModelAndView modelAndView = new ModelAndView("page/pollock7fold");
		modelAndView.addObject("listClient", clientIt);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("user", personalTraining);
		
		return modelAndView;
	}
}
