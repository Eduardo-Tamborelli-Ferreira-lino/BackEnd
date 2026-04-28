package org.example.model;

public class Motorista {
    private Integer id;
    private String nome;
    private String cnh;
    private Boolean ativo;

    public Motorista() {}

    public Motorista(String nome, String cnh, Boolean ativo) {
        this.nome = nome;
        this.cnh = cnh;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
