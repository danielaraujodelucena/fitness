package com.bitinterativo.fitness.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bitinterativo.fitness.model.Pollock7Fold;

@Repository
@Transactional
public interface Pollock7FoldRepository extends CrudRepository<Pollock7Fold, Long>{

}


