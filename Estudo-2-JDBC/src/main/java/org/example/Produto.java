package org.example;

public class Produto {

    private int id;
    private String nome;
    private Double preco;
    private int qtd;

    public Produto(int id, String nome, Double preco, int qtd) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtd = qtd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\nNome: " + getNome() + "\nPreço: " + getPreco() + "\nQuantidade: " + getQtd() + "\n------------------------------------------------------------";
    }
}
