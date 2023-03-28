package com.company.gamestoreservice.service;

import com.company.gamestoreservice.models.ProcessingFees;
import com.company.gamestoreservice.repositories.ProcessingFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProcessingFeeAdd {

    ProcessingFeeRepository processingFeeRepository;

    @Autowired
    public ProcessingFeeAdd(ProcessingFeeRepository processingFeeRepository) {
        processingFeeRepository.save(new ProcessingFees("Consoles", new BigDecimal(14.99)));
        processingFeeRepository.save(new ProcessingFees("T-Shirts", new BigDecimal(1.98)));
        processingFeeRepository.save(new ProcessingFees("Games", new BigDecimal(1.49)));
    }


}
