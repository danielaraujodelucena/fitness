package com.bitinterativo.fitness.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bitinterativo.fitness.model.Client;

@Repository
@Transactional
public interface ClientRepository extends CrudRepository<Client, Long>{
	
	@Query("select u from Client u where u.userName = ?1")
	Client findPersonByUserName(String user_name);
}


