package com.bitinterativo.fitness.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bitinterativo.fitness.model.PersonalTraining;

@Repository
@Transactional
public interface PersonalTrainingRepository extends CrudRepository<PersonalTraining, Long>{

}
