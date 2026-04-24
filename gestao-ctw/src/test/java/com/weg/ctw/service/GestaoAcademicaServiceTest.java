package com.weg.ctw.service;

import com.weg.ctw.domain.*;
import com.weg.ctw.infra.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestaoAcademicaServiceTest {

    private IAlunoRepository repositorio;
    private INotificacaoStrategy notificacaoEmail;
    private INotificacaoStrategy notificacaoSms;
    private IAlocacaoStrategy alocacaoPadrao;
    private IAlocacaoStrategy alocacaoPrioritaria;
    private GestaoAcademicaService servico;

    private Professor professor;
    private Turma turma;
    private List<Sala> salas;

    @BeforeEach
    void setUp() {
        repositorio = new AlunoRepositoryMemoria();
        notificacaoEmail = new NotificacaoEmailStrategy();
        notificacaoSms = new NotificacaoSmsStrategy();
        alocacaoPadrao = new AlocacaoPadraoStrategy();
        alocacaoPrioritaria = new AlocacaoPrioritariaStrategy();

        servico = new GestaoAcademicaService(repositorio, notificacaoEmail, alocacaoPadrao);

        professor = new ProfessorSenai(1, "Maria Souza");
        turma = new Turma(1, "Arquitetura de Software", professor);
        salas = new ArrayList<>();
        salas.add(new Sala(1, 101, 15));
        salas.add(new Sala(2, 202, 30));
        salas.add(new Sala(3, 303, 45));
    }

    @Test
    @DisplayName("Deve matricular aluno e salvá-lo no repositório")
    void deveMatricularAluno() {
        Aluno aluno = new Aluno(1, "Ana Beatriz");

        servico.matricularAluno(aluno, turma);

        List<Aluno> todos = servico.listarAlunos();
        assertEquals(1, todos.size());
        assertEquals("Ana Beatriz", todos.get(0).getNome());
    }

    @Test
    @DisplayName("Deve associar o aluno à turma ao matricular")
    void deveAssociarAlunoATurma() {
        Aluno aluno = new Aluno(1, "Emanuelle");

        servico.matricularAluno(aluno, turma);

        assertEquals(turma, aluno.getTurma());
        assertEquals(1, turma.getQuantidadeAlunos());
    }

    @Test
    @DisplayName("Deve matricular múltiplos alunos na mesma turma")
    void deveMatricularMultiplosAlunos() {
        servico.matricularAluno(new Aluno(1, "Ana"), turma);
        servico.matricularAluno(new Aluno(2, "Emanuelle"), turma);
        servico.matricularAluno(new Aluno(3, "Carlos"), turma);

        assertEquals(3, servico.listarAlunos().size());
        assertEquals(3, turma.getQuantidadeAlunos());
    }

    @Test
    @DisplayName("Deve alocar sala usando estratégia padrão")
    void deveAlocarSalaComEstrategiaPadrao() {
        turma.adicionarAluno(new Aluno(1, "Ana"));
        turma.adicionarAluno(new Aluno(2, "Emanuelle"));

        assertNull(turma.getSala());

        servico.alocarSalaTurma(turma, salas);

        assertNotNull(turma.getSala());
    }

    @Test
    @DisplayName("Deve alocar sala usando estratégia prioritária após troca")
    void deveAlocarSalaComEstrategiaPrioritaria() {
        servico.setAlocacaoStrategy(alocacaoPrioritaria);

        turma.adicionarAluno(new Aluno(1, "Ana"));
        servico.alocarSalaTurma(turma, salas);

        assertNotNull(turma.getSala());
    }

    @Test
    @DisplayName("Deve trocar estratégia de notificação em tempo de execução")
    void deveTrocarEstrategiaNotificacao() {
        assertEquals("NotificacaoEmailStrategy", servico.getNomeEstrategiaNotificacao());

        servico.setNotificacaoStrategy(notificacaoSms);

        assertEquals("NotificacaoSmsStrategy", servico.getNomeEstrategiaNotificacao());
    }

    @Test
    @DisplayName("Deve trocar estratégia de alocação em tempo de execução")
    void deveTrocarEstrategiaAlocacao() {
        assertEquals("AlocacaoPadraoStrategy", servico.getNomeEstrategiaAlocacao());

        servico.setAlocacaoStrategy(alocacaoPrioritaria);

        assertEquals("AlocacaoPrioritariaStrategy", servico.getNomeEstrategiaAlocacao());
    }

    @Test
    @DisplayName("DIP: Service funciona com qualquer implementação de IAlunoRepository")
    void dipServiceFuncionaComQualquerRepositorio() {
        IAlunoRepository outroRepo = new AlunoRepositoryMemoria();
        GestaoAcademicaService outroServico = new GestaoAcademicaService(
                outroRepo, notificacaoEmail, alocacaoPadrao
        );

        outroServico.matricularAluno(new Aluno(1, "Teste"), turma);

        assertEquals(1, outroServico.listarAlunos().size());
        assertEquals(0, servico.listarAlunos().size());
    }

    @Test
    @DisplayName("DIP: Service funciona com qualquer combinação de estratégias")
    void dipServiceFuncionaComQualquerCombinacao() {
        GestaoAcademicaService combo1 = new GestaoAcademicaService(
                repositorio, notificacaoEmail, alocacaoPadrao);
        GestaoAcademicaService combo2 = new GestaoAcademicaService(
                repositorio, notificacaoSms, alocacaoPrioritaria);

        assertNotNull(combo1);
        assertNotNull(combo2);
        assertEquals("NotificacaoEmailStrategy", combo1.getNomeEstrategiaNotificacao());
        assertEquals("AlocacaoPrioritariaStrategy", combo2.getNomeEstrategiaAlocacao());
    }

    @Test
    @DisplayName("Deve notificar professor sem lançar exceção")
    void deveNotificarProfessor() {
        assertDoesNotThrow(() ->
                servico.notificarProfessor(professor, "Sua turma foi alocada!")
        );
    }

    @Test
    @DisplayName("Deve notificar com estratégia SMS sem lançar exceção")
    void deveNotificarComSms() {
        servico.setNotificacaoStrategy(notificacaoSms);

        assertDoesNotThrow(() ->
                servico.notificarProfessor(professor, "Sala alocada via SMS")
        );
    }
}
