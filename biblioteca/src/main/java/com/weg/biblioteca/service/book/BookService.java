package com.weg.biblioteca.service.book;

import java.sql.SQLException;
import java.util.List;

import com.weg.biblioteca.model.Book;

public interface BookService {

    Book save (Book book) throws SQLException;

    Book findById(Long id) throws SQLException;

    List<Book> findAll() throws SQLException;

    void update() throws SQLException;

    void delete() throws SQLException;
}
