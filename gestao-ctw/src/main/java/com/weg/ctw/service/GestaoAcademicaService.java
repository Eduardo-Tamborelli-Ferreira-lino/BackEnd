package com.weg.ctw.service;

import com.weg.ctw.domain.*;

import java.util.List;

public class GestaoAcademicaService {

    private final IAlunoRepository alunoRepository;
    private INotificacaoStrategy notificacaoStrategy;
    private IAlocacaoStrategy alocacaoStrategy;

    public GestaoAcademicaService(
            IAlunoRepository alunoRepository,
            INotificacaoStrategy notificacaoStrategy,
            IAlocacaoStrategy alocacaoStrategy) {
        this.alunoRepository = alunoRepository;
        this.notificacaoStrategy = notificacaoStrategy;
        this.alocacaoStrategy = alocacaoStrategy;
    }

    public void setNotificacaoStrategy(INotificacaoStrategy novaEstrategia) {
        this.notificacaoStrategy = novaEstrategia;
        System.out.println("  ✔ Estratégia de notificação alterada com sucesso!");
    }

    public void setAlocacaoStrategy(IAlocacaoStrategy novaEstrategia) {
        this.alocacaoStrategy = novaEstrategia;
        System.out.println("  ✔ Estratégia de alocação alterada com sucesso!");
    }

    public void matricularAluno(Aluno aluno, Turma turma) {
        turma.adicionarAluno(aluno);
        alunoRepository.salvar(aluno);

        System.out.println("  ✔ Aluno '" + aluno.getNome() + "' matriculado na turma '"
                + turma.getNome() + "' com sucesso!");

        String mensagem = "Novo aluno matriculado: " + aluno.getNome()
                + " na turma " + turma.getNome();
        notificacaoStrategy.enviar(turma.getProfessor().getNome(), mensagem);
    }

    public void alocarSalaTurma(Turma turma, List<Sala> salasDisponiveis) {
        Sala salaAlocada = alocacaoStrategy.alocar(turma, salasDisponiveis);

        if (salaAlocada != null) {
            turma.setSala(salaAlocada);
            System.out.println("  ✔ Turma '" + turma.getNome() + "' alocada na Sala "
                    + salaAlocada.getNumero() + " (capacidade: " + salaAlocada.getCapacidade() + ")");
        } else {
            System.out.println("  ✘ Nenhuma sala disponível com capacidade suficiente para a turma '"
                    + turma.getNome() + "' (" + turma.getQuantidadeAlunos() + " alunos)");
        }
    }

    public void notificarProfessor(Professor professor, String mensagem) {
        notificacaoStrategy.enviar(professor.getNome(), mensagem);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.listarTodos();
    }

    public String getNomeEstrategiaNotificacao() {
        return notificacaoStrategy.getClass().getSimpleName();
    }

    public String getNomeEstrategiaAlocacao() {
        return alocacaoStrategy.getClass().getSimpleName();
    }
}
