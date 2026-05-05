package com.weg.biblioteca.service.loan;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.biblioteca.model.Loan;
import com.weg.biblioteca.repository.book.BookRepository;
import com.weg.biblioteca.repository.loan.LoanRepository;
import com.weg.biblioteca.repository.user.UserRepository;

@Service
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    public LoanServiceImpl(LoanRepository loanRepository, UserRepository userRepository,
            BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Loan save(Loan loan) throws SQLException {

        if (userRepository.userExist(loan.getUser_id()) && 
        bookRepository.bookExist(loan.getBook_id())) {

            loanRepository.save(loan);

            return loan;
        }

        throw new RuntimeException("Same information is null");
    }

    @Override
    public Loan findById(Long id) throws SQLException {

        Loan loan = loanRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Id not found"));

        return loan;
    }

    @Override
    public List<Loan> findAll() throws SQLException {

        List<Loan> loans = loanRepository.findAll();

        if (loans == null || loans.isEmpty()) {
            throw new RuntimeException("Don't has any loan on system");
        }

        return loans;
    }

    @Override
    public Loan update(Long id, Loan loan) throws SQLException {
        
        if (!loanRepository.loanExist(id)) {
            throw new RuntimeException("Loan not found.");
        }

        loan.setId(id);

        loanRepository.update(loan);

        return loan;
    }

    @Override
    public Loan returnBook(Long id, Loan loan) throws SQLException {

        if (!loanRepository.loanExist(id)) {
            throw new RuntimeException("Loan not found.");
        }

        loan.setId(id);

        loanRepository.returnBook(loan);

        return loan;
    }

    @Override
    public void delete(Long id) throws SQLException {

        if (!loanRepository.loanExist(id)) {
            throw new RuntimeException("Loan not found.");
        }

        loanRepository.delete(id);

    }

}
