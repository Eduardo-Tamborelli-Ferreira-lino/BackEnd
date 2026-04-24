package com.weg.ctw.domain;

public class Aluno {

    private int id;
    private String nome;
    private Turma turma;

    public Aluno(int id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        String nomeTurma = (turma != null) ? turma.getNome() : "Sem turma";
        return "Aluno{id=" + id + ", nome='" + nome + "', turma='" + nomeTurma + "'}";
    }
}
