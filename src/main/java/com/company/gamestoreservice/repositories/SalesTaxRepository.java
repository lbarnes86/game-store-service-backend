package com.company.gamestoreservice.repositories;

import com.company.gamestoreservice.models.SalesTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesTaxRepository extends JpaRepository<SalesTax, String> {
}
