package com.bitinterativo.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bitinterativo.fitness.model.ReportPollock7;

@Repository
@Transactional
public interface ReportPollock7Repository extends CrudRepository<ReportPollock7, Long>{
	
	@Query(value = "SELECT * FROM report_pollock7 WHERE id = (:id);", nativeQuery = true)
	List<ReportPollock7> findReportPollock7ById(Long id);
}


