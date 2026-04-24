package org.example.Model;

public class Livro {
    private int id;
    private int autorId;
    private String titulo;
    private Integer anoPublicacao;

    public Livro(int id, int autorId, String titulo, Integer anoPublicacao) {
        this.id = id;
        this.autorId = autorId;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
    }

    public Livro(int autorId, String titulo, Integer anoPublicacao) {
        this.autorId = autorId;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
    }

    public Livro(String titulo, Integer anoPublicacao) {
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

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
}
