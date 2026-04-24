package org.example.Model;

public class Livro {
    private int id;
    private int autorId;
    private String titulo;
    private int anoPublicacao;

    public Livro(int id, int autorId, String titulo, int anoPublicacao) {
        this.id = id;
        this.autorId = autorId;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
    }

    public Livro(int autorId, String titulo, int anoPublicacao) {
        this.autorId = autorId;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
    }

    public Livro(String titulo, int anoPublicacao) {
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
}
