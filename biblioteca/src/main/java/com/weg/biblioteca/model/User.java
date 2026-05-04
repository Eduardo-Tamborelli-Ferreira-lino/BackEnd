package com.weg.biblioteca.model;

public class User {

    private Long id;

    private String name;

    private String email;

    public User(Long id, String nome, String email) {
        this.id = id;
        this.name = nome;
        this.email = email;
    }

    public User(String nome, String email) {
        this.name = nome;
        this.email = email;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
