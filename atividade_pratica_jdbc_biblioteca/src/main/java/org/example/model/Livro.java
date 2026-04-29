package org.example.model;

public class Livro {
    private Integer id;
    private String titulo;
    private String isbn;
    private String status;

    public Livro() {
    }

    public Livro(Integer id, String titulo, String isbn, String status) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
