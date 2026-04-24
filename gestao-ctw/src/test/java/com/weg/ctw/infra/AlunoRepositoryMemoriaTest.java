package com.weg.ctw.infra;

import com.weg.ctw.domain.Aluno;
import com.weg.ctw.domain.IAlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlunoRepositoryMemoriaTest {

    private IAlunoRepository repositorio;

    @BeforeEach
    void setUp() {
        repositorio = new AlunoRepositoryMemoria();
    }

    @Test
    @DisplayName("Deve salvar um aluno e recuperá-lo por ID")
    void deveSalvarERecuperar() {
        Aluno aluno = new Aluno(1, "Ana Beatriz");
        repositorio.salvar(aluno);

        Aluno encontrado = repositorio.buscarPorId(1);

        assertNotNull(encontrado);
        assertEquals("Ana Beatriz", encontrado.getNome());
    }

    @Test
    @DisplayName("Deve retornar null quando buscar ID inexistente")
    void deveRetornarNullParaIdInexistente() {
        Aluno resultado = repositorio.buscarPorId(999);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Deve listar todos os alunos salvos")
    void deveListarTodosAlunos() {
        repositorio.salvar(new Aluno(1, "Ana"));
        repositorio.salvar(new Aluno(2, "Emanuelle"));
        repositorio.salvar(new Aluno(3, "Carlos"));

        List<Aluno> todos = repositorio.listarTodos();

        assertEquals(3, todos.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não há alunos")
    void deveRetornarListaVazia() {
        List<Aluno> todos = repositorio.listarTodos();

        assertNotNull(todos);
        assertTrue(todos.isEmpty());
    }

    @Test
    @DisplayName("Deve remover um aluno por ID")
    void deveRemoverAluno() {
        repositorio.salvar(new Aluno(1, "Ana"));
        repositorio.salvar(new Aluno(2, "Emanuelle"));

        boolean removido = repositorio.remover(1);

        assertTrue(removido);
        assertNull(repositorio.buscarPorId(1));
        assertEquals(1, repositorio.listarTodos().size());
    }

    @Test
    @DisplayName("Deve retornar false ao tentar remover ID inexistente")
    void deveRetornarFalseSeIdNaoExiste() {
        boolean removido = repositorio.remover(999);

        assertFalse(removido);
    }

    @Test
    @DisplayName("Deve atualizar aluno existente ao salvar com mesmo ID")
    void deveAtualizarAlunoExistente() {
        repositorio.salvar(new Aluno(1, "Nome Antigo"));
        repositorio.salvar(new Aluno(1, "Nome Novo"));

        List<Aluno> todos = repositorio.listarTodos();
        assertEquals(1, todos.size());
        assertEquals("Nome Novo", todos.get(0).getNome());
    }
}
