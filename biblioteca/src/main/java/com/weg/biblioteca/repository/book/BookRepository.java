package com.weg.biblioteca.repository.book;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.biblioteca.model.Book;

public interface BookRepository {

    Book save (Book book) throws SQLException;

    Optional<Book> findById(Long id) throws SQLException;

    List<Book> findAll() throws SQLException;

    Boolean bookExist(Long id) throws SQLException;

    void update(Book book) throws SQLException;

    void delete(Long id) throws SQLException;
}
