package com.bitinterativo.fitness.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bitinterativo.fitness.model.PersonalTraining;

@Repository
@Transactional
public interface PersonRepository extends CrudRepository<PersonalTraining, Long>{
	
	@Query("select u from PersonalTraining u where u.userName = ?1")
	PersonalTraining findPersonalTrainingByUserName(String user_name);
	
	@Query("select u from Client u where u.userName = ?1")
	PersonalTraining findClientByUserName(String user_name);
}


