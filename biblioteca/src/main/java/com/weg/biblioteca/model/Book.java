package com.weg.biblioteca.model;

public class Book {

    private Long id;

    private String title;

    private String author;

    private int yearPublication;

    public Book(Long id, String title, String author, int yearPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublication = yearPublication;
    }

    public Book(String title, String author, int yearPublication) {
        this.title = title;
        this.author = author;
        this.yearPublication = yearPublication;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    
}