package com.weg.ctw.dto;

import com.weg.ctw.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DtoMapperTest {

    @Test
    @DisplayName("Deve converter Aluno para AlunoDTO corretamente")
    void deveConverterAlunoParaDTO() {
        Professor prof = new ProfessorWeg(1, "Carlos");
        Turma turma = new Turma(1, "BD", prof);
        Aluno aluno = new Aluno(1, "Ana Beatriz");
        turma.adicionarAluno(aluno);

        AlunoDTO dto = DtoMapper.toAlunoDTO(aluno);

        assertEquals(1, dto.getId());
        assertEquals("Ana Beatriz", dto.getNome());
        assertEquals("BD", dto.getNomeTurma());
    }

    @Test
    @DisplayName("Deve converter Aluno sem turma para AlunoDTO com 'Sem turma'")
    void deveConverterAlunoSemTurma() {
        Aluno aluno = new Aluno(1, "Emanuelle");

        AlunoDTO dto = DtoMapper.toAlunoDTO(aluno);

        assertEquals("Sem turma", dto.getNomeTurma());
    }

    @Test
    @DisplayName("Deve converter Turma para TurmaDTO corretamente")
    void deveConverterTurmaParaDTO() {
        Professor prof = new ProfessorSenai(1, "Maria Souza");
        Turma turma = new Turma(1, "Arq. Software", prof);
        turma.adicionarAluno(new Aluno(1, "Ana"));
        turma.adicionarAluno(new Aluno(2, "Emanuelle"));

        Sala sala = new Sala(1, 202, 30);
        turma.setSala(sala);

        TurmaDTO dto = DtoMapper.toTurmaDTO(turma);

        assertEquals(1, dto.getId());
        assertEquals("Arq. Software", dto.getNome());
        assertEquals("Maria Souza", dto.getNomeProfessor());
        assertEquals("SENAI", dto.getTipoProfessor());
        assertEquals(2, dto.getQuantidadeAlunos());
        assertEquals("Sala 202", dto.getSalaAlocada());
    }

    @Test
    @DisplayName("Deve converter Turma sem sala para TurmaDTO com 'Sem sala'")
    void deveConverterTurmaSemSala() {
        Professor prof = new ProfessorWeg(1, "Carlos");
        Turma turma = new Turma(1, "BD", prof);

        TurmaDTO dto = DtoMapper.toTurmaDTO(turma);

        assertEquals("Sem sala", dto.getSalaAlocada());
        assertEquals(0, dto.getQuantidadeAlunos());
    }

    @Test
    @DisplayName("AlunoDTO deve ter todos os getters e setters funcionais")
    void alunoDtoGettersSetters() {
        AlunoDTO dto = new AlunoDTO();
        dto.setId(5);
        dto.setNome("Teste");
        dto.setNomeTurma("Turma X");

        assertEquals(5, dto.getId());
        assertEquals("Teste", dto.getNome());
        assertEquals("Turma X", dto.getNomeTurma());
    }

    @Test
    @DisplayName("TurmaDTO deve ter todos os getters e setters funcionais")
    void turmaDtoGettersSetters() {
        TurmaDTO dto = new TurmaDTO();
        dto.setId(3);
        dto.setNome("Redes");
        dto.setNomeProfessor("João");
        dto.setTipoProfessor("WEG");
        dto.setQuantidadeAlunos(25);
        dto.setSalaAlocada("Sala 303");

        assertEquals(3, dto.getId());
        assertEquals("Redes", dto.getNome());
        assertEquals("João", dto.getNomeProfessor());
        assertEquals("WEG", dto.getTipoProfessor());
        assertEquals(25, dto.getQuantidadeAlunos());
        assertEquals("Sala 303", dto.getSalaAlocada());
    }
}
