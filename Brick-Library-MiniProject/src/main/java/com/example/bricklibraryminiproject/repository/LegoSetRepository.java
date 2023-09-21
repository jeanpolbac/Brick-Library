package com.example.bricklibraryminiproject.repository;

import com.example.bricklibraryminiproject.model.LegoSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegoSetRepository extends JpaRepository<LegoSet, Long> {

}
