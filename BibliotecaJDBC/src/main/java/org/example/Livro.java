package org.example;

public class Livro {
    private int id;
    private String titulo;
    private int quantidade;

    public Livro(int id, String titulo, int quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.quantidade = quantidade;
    }

    public Livro(String titulo, int quantidade) {
        this.titulo = titulo;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\nTítulo: " + getTitulo() + "\nQuantidade Disponivel: " + getQuantidade() + "\n---------------------------------------------------------";
    }
}
