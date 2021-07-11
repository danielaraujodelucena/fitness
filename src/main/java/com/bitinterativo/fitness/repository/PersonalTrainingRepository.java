package com.bitinterativo.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bitinterativo.fitness.model.PersonalTraining;

@Repository
@Transactional
public interface PersonalTrainingRepository extends CrudRepository<PersonalTraining, Long>{
	
	@Query("select u from PersonalTraining u where u.type = 'PERSONAL-TRAINING'")
	List<PersonalTraining> findAllPersonalTraining();
	
	@Query("select u from PersonalTraining u where u.userName = ?1")
	PersonalTraining findPersonByUserName(String user_name);
	
	@Modifying
	@Query(value = "INSERT INTO person_role (person_id, role_id) VALUES (:person_id, :role_id);", nativeQuery = true)
	void savePersonRole(@Param("person_id") Long person_id, @Param("role_id") Long role_id);
	
	@Modifying
	@Query(value = "DELETE FROM person_role WHERE person_id = (:personId);", nativeQuery = true)
	void deletePersonRole(@Param("personId") Long personId);
}

