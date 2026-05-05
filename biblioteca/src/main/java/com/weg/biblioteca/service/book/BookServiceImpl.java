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

        Book book = bookRepository.findById(id).
            orElseThrow(() -> new RuntimeException("ID not found on data base."));

        return book;
    }

    @Override
    public List<Book> findAll() throws SQLException {
        
        List<Book> books = bookRepository.findAll();

        if (books == null || books.isEmpty()) {
            throw new RuntimeException("Nome book found on data base.");
        }

        return books;
    }

    @Override
    public Book update(Long id, Book book) throws SQLException {

        if (!bookRepository.bookExist(id)) {
            throw new RuntimeException("Book not found");
        }

        book.setId(id);

        bookRepository.update(book);
        
        return book;
    }

    @Override
    public void delete(Long id) throws SQLException {

        if (!bookRepository.bookExist(id)) {
            throw new RuntimeException("Book not found");
        }

        bookRepository.delete(id);
    }

}
