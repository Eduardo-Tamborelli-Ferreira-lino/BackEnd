package com.weg.ctw.domain;

import java.util.ArrayList;
import java.util.List;

public class Turma {

    private int id;
    private String nome;
    private Professor professor;
    private Sala sala;
    private List<Aluno> alunos;

    public Turma(int id, String nome, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
        aluno.setTurma(this);
    }

    public int getQuantidadeAlunos() {
        return alunos.size();
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    @Override
    public String toString() {
        String nomeSala = (sala != null) ? "Sala " + sala.getNumero() : "Sem sala";
        return "Turma{id=" + id + ", nome='" + nome + "', professor='" + professor.getNome()
                + "', sala='" + nomeSala + "', alunos=" + alunos.size() + "}";
    }
}
