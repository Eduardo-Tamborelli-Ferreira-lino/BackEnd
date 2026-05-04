package com.weg.biblioteca.service.book;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.biblioteca.model.Book;
import com.weg.biblioteca.repository.book.BookRepository;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) throws SQLException {

        if (book.getTitle() == null || book.getAuthor() == null || book.getTitle().isEmpty() || book.getAuthor().isEmpty() || book.getYearPublication() <= 0) {
            throw new RuntimeException("Title, Author and Year can't be null or empty");
        }

        bookRepository.save(book);

        return book;

    }

    @Override
    public Book findById(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Book> findAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void update() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
