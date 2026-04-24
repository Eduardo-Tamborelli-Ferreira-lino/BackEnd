package com.weg.ctw.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntidadesDomainTest {

    @Test
    @DisplayName("Deve criar um Aluno com id e nome corretamente")
    void deveCriarAluno() {
        Aluno aluno = new Aluno(1, "Ana Beatriz");

        assertEquals(1, aluno.getId());
        assertEquals("Ana Beatriz", aluno.getNome());
        assertNull(aluno.getTurma());
    }

    @Test
    @DisplayName("Deve permitir alterar o nome do Aluno")
    void deveAlterarNomeAluno() {
        Aluno aluno = new Aluno(1, "Nome Antigo");
        aluno.setNome("Nome Novo");

        assertEquals("Nome Novo", aluno.getNome());
    }

    @Test
    @DisplayName("Deve criar uma Sala com numero e capacidade")
    void deveCriarSala() {
        Sala sala = new Sala(1, 101, 30);

        assertEquals(1, sala.getId());
        assertEquals(101, sala.getNumero());
        assertEquals(30, sala.getCapacidade());
    }

    @Test
    @DisplayName("ProfessorWeg deve ter carga horária de 20h")
    void professorWegDeveTerCarga20h() {
        Professor prof = new ProfessorWeg(1, "Carlos Silva");

        assertEquals(20.0, prof.getCargaHorariaMaxima());
        assertEquals("WEG", prof.getTipo());
    }

    @Test
    @DisplayName("ProfessorSenai deve ter carga horária de 40h")
    void professorSenaiDeveTerCarga40h() {
        Professor prof = new ProfessorSenai(2, "Maria Souza");

        assertEquals(40.0, prof.getCargaHorariaMaxima());
        assertEquals("SENAI", prof.getTipo());
    }

    @Test
    @DisplayName("LSP: ProfessorWeg e ProfessorSenai devem ser substituíveis por Professor")
    void lspProfessoresDevemSerSubstituiveis() {
        Professor profWeg = new ProfessorWeg(1, "Carlos");
        Professor profSenai = new ProfessorSenai(2, "Maria");

        assertDoesNotThrow(() -> profWeg.getCargaHorariaMaxima());
        assertDoesNotThrow(() -> profSenai.getCargaHorariaMaxima());
        assertDoesNotThrow(() -> profWeg.getTipo());
        assertDoesNotThrow(() -> profSenai.getTipo());

        assertTrue(profWeg.getCargaHorariaMaxima() > 0);
        assertTrue(profSenai.getCargaHorariaMaxima() > 0);
    }

    @Test
    @DisplayName("Deve criar uma Turma e adicionar alunos corretamente")
    void deveCriarTurmaEAdicionarAlunos() {
        Professor prof = new ProfessorSenai(1, "Maria Souza");
        Turma turma = new Turma(1, "Arquitetura de Software", prof);

        assertEquals(0, turma.getQuantidadeAlunos());

        Aluno aluno1 = new Aluno(1, "Ana");
        Aluno aluno2 = new Aluno(2, "Emanuelle");
        turma.adicionarAluno(aluno1);
        turma.adicionarAluno(aluno2);

        assertEquals(2, turma.getQuantidadeAlunos());
        assertEquals(turma, aluno1.getTurma());
        assertEquals(turma, aluno2.getTurma());
    }

    @Test
    @DisplayName("Turma deve iniciar sem sala alocada")
    void turmaDeveIniciarSemSala() {
        Professor prof = new ProfessorWeg(1, "Carlos");
        Turma turma = new Turma(1, "BD", prof);

        assertNull(turma.getSala());
    }

    @Test
    @DisplayName("Deve alocar sala na turma")
    void deveAlocarSalaNaTurma() {
        Professor prof = new ProfessorWeg(1, "Carlos");
        Turma turma = new Turma(1, "BD", prof);
        Sala sala = new Sala(1, 202, 30);

        turma.setSala(sala);

        assertNotNull(turma.getSala());
        assertEquals(202, turma.getSala().getNumero());
    }
}
