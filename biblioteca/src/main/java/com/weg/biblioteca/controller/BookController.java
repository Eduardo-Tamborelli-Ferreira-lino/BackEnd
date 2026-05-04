package com.weg.biblioteca.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.biblioteca.model.Book;
import com.weg.biblioteca.service.book.BookService;

@RestController
@RequestMapping("/library")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
        
        try{
            return bookService.save(book);
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
