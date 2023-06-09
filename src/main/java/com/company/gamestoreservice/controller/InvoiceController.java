package com.company.gamestoreservice.controller;

import com.company.gamestoreservice.models.Invoices;
import com.company.gamestoreservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {

    @Autowired
    private ServiceLayer serviceLayer;

    @GetMapping(value = "/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoices> getAllInvoices() {
        return serviceLayer.getAllInvoices();
    }

    @GetMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Invoices> getInvoicesById(@PathVariable int id) {
        return serviceLayer.getInvoicesById(id);
    }

    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoices createInvoices(@Valid @RequestBody Invoices invoices) {
        return serviceLayer.addInvoices(invoices);
    }

    @PutMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Invoices updateInvoices(@RequestBody Invoices invoices) {
        return serviceLayer.updateInvoices(invoices);
    }
    @DeleteMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Invoices deleteInvoices(@PathVariable int id) {
        serviceLayer.deleteInvoices(id);
        return null;
    }
}
