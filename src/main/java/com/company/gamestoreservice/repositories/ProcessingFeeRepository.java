package com.company.gamestoreservice.repositories;

import com.company.gamestoreservice.models.ProcessingFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingFeeRepository extends JpaRepository<ProcessingFees, String> {
}
