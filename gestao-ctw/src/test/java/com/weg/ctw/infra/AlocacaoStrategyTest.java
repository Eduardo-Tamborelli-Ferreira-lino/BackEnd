package com.weg.ctw.infra;

import com.weg.ctw.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlocacaoStrategyTest {

    private List<Sala> criarSalasDisponiveis() {
        List<Sala> salas = new ArrayList<>();
        salas.add(new Sala(1, 101, 15));
        salas.add(new Sala(2, 202, 30));
        salas.add(new Sala(3, 303, 45));
        salas.add(new Sala(4, 404, 60));
        return salas;
    }

    private Turma criarTurmaComAlunos(int qtdAlunos) {
        Professor prof = new ProfessorSenai(1, "Maria");
        Turma turma = new Turma(1, "Teste", prof);
        for (int i = 1; i <= qtdAlunos; i++) {
            turma.adicionarAluno(new Aluno(i, "Aluno " + i));
        }
        return turma;
    }

    @Test
    @DisplayName("Padrão: deve alocar a PRIMEIRA sala com capacidade suficiente")
    void padraoDeveAlocarPrimeiraSala() {
        IAlocacaoStrategy estrategia = new AlocacaoPadraoStrategy();
        List<Sala> salas = criarSalasDisponiveis();
        Turma turma = criarTurmaComAlunos(10);

        Sala resultado = estrategia.alocar(turma, salas);

        assertNotNull(resultado);
        assertEquals(101, resultado.getNumero());
    }

    @Test
    @DisplayName("Padrão: deve retornar null quando nenhuma sala comporta a turma")
    void padraoDeveRetornarNullSemCapacidade() {
        IAlocacaoStrategy estrategia = new AlocacaoPadraoStrategy();
        List<Sala> salas = criarSalasDisponiveis();
        Turma turma = criarTurmaComAlunos(100);

        Sala resultado = estrategia.alocar(turma, salas);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Prioritária: deve alocar a MENOR sala que comporte a turma")
    void prioritariaDeveAlocarMenorSala() {
        IAlocacaoStrategy estrategia = new AlocacaoPrioritariaStrategy();
        List<Sala> salas = criarSalasDisponiveis();
        Turma turma = criarTurmaComAlunos(20);

        Sala resultado = estrategia.alocar(turma, salas);

        assertNotNull(resultado);
        assertEquals(202, resultado.getNumero());
    }

    @Test
    @DisplayName("Prioritária: deve retornar null quando nenhuma sala comporta")
    void prioritariaDeveRetornarNullSemCapacidade() {
        IAlocacaoStrategy estrategia = new AlocacaoPrioritariaStrategy();
        List<Sala> salas = criarSalasDisponiveis();
        Turma turma = criarTurmaComAlunos(100);

        Sala resultado = estrategia.alocar(turma, salas);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Strategy: ambas as estratégias devem ser intercambiáveis")
    void estrategiasDevemSerIntercambiaveis() {
        List<Sala> salas = criarSalasDisponiveis();
        Turma turma = criarTurmaComAlunos(20);

        IAlocacaoStrategy padrao = new AlocacaoPadraoStrategy();
        IAlocacaoStrategy prioritaria = new AlocacaoPrioritariaStrategy();

        Sala resultadoPadrao = padrao.alocar(turma, salas);
        Sala resultadoPrioritaria = prioritaria.alocar(turma, salas);

        assertNotNull(resultadoPadrao);
        assertNotNull(resultadoPrioritaria);

        assertEquals(resultadoPadrao.getNumero(), resultadoPrioritaria.getNumero());
    }

    @Test
    @DisplayName("Strategy: estratégias retornam resultados diferentes para turma de 10 alunos")
    void estrategiasPodemRetornarResultadosDiferentes() {
        List<Sala> salas = new ArrayList<>();
        salas.add(new Sala(1, 401, 60));
        salas.add(new Sala(2, 201, 25));
        salas.add(new Sala(3, 301, 40));

        Turma turma = criarTurmaComAlunos(10);

        IAlocacaoStrategy padrao = new AlocacaoPadraoStrategy();
        IAlocacaoStrategy prioritaria = new AlocacaoPrioritariaStrategy();

        Sala resultadoPadrao = padrao.alocar(turma, salas);
        Sala resultadoPrioritaria = prioritaria.alocar(turma, salas);

        assertEquals(401, resultadoPadrao.getNumero());
        assertEquals(201, resultadoPrioritaria.getNumero());

        assertNotEquals(resultadoPadrao.getNumero(), resultadoPrioritaria.getNumero());
    }
}
