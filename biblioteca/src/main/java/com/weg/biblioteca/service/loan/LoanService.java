package com.weg.biblioteca.service.loan;

import java.sql.SQLException;
import java.util.List;

import com.weg.biblioteca.model.Loan;

public interface LoanService {

    Loan save(Loan loan) throws SQLException;

    Loan findById(Long id) throws SQLException;

    List<Loan> findAll() throws SQLException;

    Loan update(Long id, Loan loan) throws SQLException;

    Loan returnBook(Long id, Loan loan) throws SQLException;

    void delete(Long id) throws SQLException; 
}
