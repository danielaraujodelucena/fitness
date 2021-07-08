package com.bitinterativo.fitness.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bitinterativo.fitness.model.Person;
import com.bitinterativo.fitness.model.PersonalTraining;
import com.bitinterativo.fitness.repository.PersonalTrainingRepository;

@Service
@Transactional
public class PersonalTrainingDetailsService implements UserDetailsService {

	@Autowired
	private PersonalTrainingRepository personalTrainingRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PersonalTraining personalTraining = personalTrainingRepository.findPersonByUserName(username);
		
		if(personalTraining == null) {
			throw new UsernameNotFoundException("Pessoa não encontrada");
		}
		
		return new User(personalTraining.getUserName(), personalTraining.getPassword(), personalTraining.isEnabled(), true, true, true, personalTraining.getAuthorities());
	}

}
