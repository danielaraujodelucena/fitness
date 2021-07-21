package com.bitinterativo.fitness.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
import com.bitinterativo.fitness.model.PhysicalTestPerson;
import com.bitinterativo.fitness.model.Pollock7Fold;
import com.bitinterativo.fitness.model.ReportPollock7;
import com.bitinterativo.fitness.report.ReportUtil;
import com.bitinterativo.fitness.repository.ClientRepository;
import com.bitinterativo.fitness.repository.PersonalTrainingRepository;
import com.bitinterativo.fitness.repository.PhysicalTestPersonRepository;
import com.bitinterativo.fitness.repository.Pollock7FoldRepository;
import com.bitinterativo.fitness.repository.ReportPollock7Repository;

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
	
	@Autowired
	private ReportPollock7Repository reportPollock7Repository;
	
	@Autowired
	private ReportUtil reportUtil;
	
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
	
	@RequestMapping(method=RequestMethod.POST, value="**/generatePdfPollock7Client")
	public void generatePdfPollock7Client(@RequestParam("id_personalTraining") String idPersonalTraining, @RequestParam("idClient") String idClient, @RequestParam("id") String idTest, HttpServletRequest request, HttpServletResponse response) {
		ReportPollock7 reportPollock = new ReportPollock7();
		ReportPollock7 reportPollockSave = null;
		SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		String dateAssessmentString = null;
		String dateBirthdayString = null;
		
		Optional<Client> client = clientRepository.findById(Long.parseLong(idClient));
		Optional<PersonalTraining> personal = personalTrainingRepository.findById(Long.parseLong(idPersonalTraining));
		Optional<Pollock7Fold> pollock7 = pollock7FoldRepository.findById(Long.parseLong(idTest));
		
		reportPollock.setNamePersonalTraining(personal.get().getName());
		reportPollock.setCref(personal.get().getCref());
		reportPollock.setPhone(personal.get().getPhone());
		reportPollock.setEmail(personal.get().getUsername());
		
		reportPollock.setSubscapular(pollock7.get().getSubscapular());
		reportPollock.setTriceps(pollock7.get().getTriceps());
		reportPollock.setPectoral(pollock7.get().getPectoral());
		reportPollock.setMidAxiliary(pollock7.get().getMidAxiliary());
		reportPollock.setThigh(pollock7.get().getThigh());
		reportPollock.setSuprailiac(pollock7.get().getSuprailiac());
		reportPollock.setAbdomen(pollock7.get().getAbdomen());
		reportPollock.setWeight(pollock7.get().getWeight());
		reportPollock.setHeight(pollock7.get().getHeight());
		
		reportPollock.setNameClient(client.get().getName());
		reportPollock.setSex(client.get().getSex());
		
		try {
			dateAssessmentString = out.format(in.parse(pollock7.get().getDate().toString()));
			reportPollock.setDateAvaliacao(dateAssessmentString);
			
			dateBirthdayString = out.format(in.parse(client.get().getBirthDate().toString()));
			
			Date birthday = new SimpleDateFormat("dd/MM/yyyy").parse(dateBirthdayString);
			LocalDate dateTrans = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int age = Period.between(dateTrans, LocalDate.now()).getYears();
			
			reportPollock.setAge(String.valueOf(age));
			
			double resultBodyDensity = reportPollock.resultBodyDensity(age, client.get().getSex());
			
			reportPollock.setBodyDensity(resultBodyDensity);
			reportPollock.setFatLevel(reportPollock.resultFatLevel(age, client.get().getSex(), resultBodyDensity));
			reportPollock.setNameAvaliacao(pollock7.get().getNamePhysicalTest() + ", em " + reportPollock.getDateAvaliacao());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		reportPollockSave = reportPollock7Repository.save(reportPollock);
		
		List<ReportPollock7> list = reportPollock7Repository.findReportPollock7ById(reportPollockSave.getId());
		
		try {
			byte[] pdf = reportUtil.gerarRelatorio(list, "pollock7", request.getServletContext());
			
			response.setContentLength(pdf.length);
			response.setContentType("application/octet-stream");
			
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", list.get(0).getNameClient()+"-pollock7.pdf");
			
			response.setHeader(headerKey, headerValue);
			
			response.getOutputStream().write(pdf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
