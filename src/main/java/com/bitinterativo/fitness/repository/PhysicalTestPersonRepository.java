package com.bitinterativo.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bitinterativo.fitness.model.PhysicalTestPerson;

@Repository
@Transactional
public interface PhysicalTestPersonRepository extends CrudRepository<PhysicalTestPerson, Long>{
	
	@Modifying
	@Query(value = "SELECT"
			+ "	physicaltest.id,"
			+ "	physicaltest.date,"
			+ "	physicaltest.id_client,"
			+ "	person.name"
			+ " FROM"
			+ "	physicaltest"
			+ " INNER JOIN"
			+ "	person ON  person.id = physicaltest.id_client"
			+ " WHERE"
			+ "	physicaltest.name_physical_test = (:name_physical_test) AND physicaltest.id_personal_training = (:id_personal_training);", nativeQuery = true)
	List<PhysicalTestPerson> findAll2(@Param("name_physical_test") String name_physical_test, @Param("id_personal_training") Long id_personal_training);

}


