package com.weg.ctw.dto;

public class TurmaDTO {

    private int id;
    private String nome;
    private String nomeProfessor;
    private String tipoProfessor;
    private int quantidadeAlunos;
    private String salaAlocada;

    public TurmaDTO() {
    }

    public TurmaDTO(int id, String nome, String nomeProfessor, String tipoProfessor,
                    int quantidadeAlunos, String salaAlocada) {
        this.id = id;
        this.nome = nome;
        this.nomeProfessor = nomeProfessor;
        this.tipoProfessor = tipoProfessor;
        this.quantidadeAlunos = quantidadeAlunos;
        this.salaAlocada = salaAlocada;
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

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getTipoProfessor() {
        return tipoProfessor;
    }

    public void setTipoProfessor(String tipoProfessor) {
        this.tipoProfessor = tipoProfessor;
    }

    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public String getSalaAlocada() {
        return salaAlocada;
    }

    public void setSalaAlocada(String salaAlocada) {
        this.salaAlocada = salaAlocada;
    }

    @Override
    public String toString() {
        return "TurmaDTO{id=" + id + ", nome='" + nome + "', professor='" + nomeProfessor
                + " (" + tipoProfessor + ")', alunos=" + quantidadeAlunos
                + ", sala='" + salaAlocada + "'}";
    }
}
