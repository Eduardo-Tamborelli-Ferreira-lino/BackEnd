package com.weg.ctw.dto;

public class AlunoDTO {

    private int id;
    private String nome;
    private String nomeTurma;

    public AlunoDTO() {
    }

    public AlunoDTO(int id, String nome, String nomeTurma) {
        this.id = id;
        this.nome = nome;
        this.nomeTurma = nomeTurma;
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

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    @Override
    public String toString() {
        return "AlunoDTO{id=" + id + ", nome='" + nome + "', turma='" + nomeTurma + "'}";
    }
}
