package com.weg.ctw.domain;

public abstract class Professor {

    private int id;
    private String nome;

    public Professor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public abstract double getCargaHorariaMaxima();

    public abstract String getTipo();

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

    @Override
    public String toString() {
        return "Professor{id=" + id + ", nome='" + nome + "', tipo='" + getTipo()
                + "', cargaMax=" + getCargaHorariaMaxima() + "h}";
    }
}
