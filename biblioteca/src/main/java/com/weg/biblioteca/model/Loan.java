package com.weg.biblioteca.model;

import java.time.LocalDate;

public class Loan {

    private Long id;
    private Long book_id;
    private Long user_id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    
    public Loan(Long id, Long book_id, Long user_id, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.book_id = book_id;
        this.user_id = user_id;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Loan(Long book_id, Long user_id, LocalDate loanDate, LocalDate returnDate) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    
}
