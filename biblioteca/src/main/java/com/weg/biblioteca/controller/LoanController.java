package com.weg.biblioteca.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.biblioteca.model.Loan;
import com.weg.biblioteca.service.loan.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan save(@RequestBody Loan loan) {
        
        try{
            loanService.save(loan);

            return loan;
        }
        catch(SQLException | RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Loan findById(@PathVariable Long id) {
        try{
            return loanService.findById(id);
        }
        catch(SQLException | RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Loan> findAll() {
        try{
            return loanService.findAll();
        }
        catch(SQLException | RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Loan update(@PathVariable Long id, @RequestBody Loan loan) {
                
        try{
            return loanService.update(id, loan);
        }
        catch(SQLException | RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}/return")
    public Loan returnBook(@PathVariable Long id, @RequestBody Loan loan) {
                
        try{
            return loanService.returnBook(id, loan);
        }
        catch(SQLException | RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        
        try{
            loanService.delete(id);
        }
        catch(SQLException | RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
