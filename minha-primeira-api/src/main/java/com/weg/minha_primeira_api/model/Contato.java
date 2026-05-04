package com.weg.minha_primeira_api.model;

public class Contato {

    private Long id;

    private String nome;

    private String numero;

    public Contato(Long id, String nome, String numero) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
    }

    public Contato(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public Contato() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    
}
