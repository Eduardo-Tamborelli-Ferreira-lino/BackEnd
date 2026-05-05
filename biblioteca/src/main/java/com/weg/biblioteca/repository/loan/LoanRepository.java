package com.weg.biblioteca.repository.loan;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.biblioteca.model.Loan;

public interface LoanRepository {

    Loan save(Loan loan) throws SQLException;

    Optional<Loan> findById(Long id) throws SQLException;

    List<Loan> findAll() throws SQLException;

    Boolean loanExist(Long id) throws SQLException;

    void update(Loan loan) throws SQLException;

    void returnBook(Loan loan) throws SQLException;

    void delete(Long id) throws SQLException;
}
