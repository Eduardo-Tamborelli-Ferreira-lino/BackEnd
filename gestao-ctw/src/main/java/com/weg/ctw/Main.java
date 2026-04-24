package com.weg.ctw;

import com.weg.ctw.domain.*;
import com.weg.ctw.infra.*;
import com.weg.ctw.legado.SistemaLegado;
import com.weg.ctw.service.GestaoAcademicaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int proximoIdAluno = 1;
    private static int proximoIdTurma = 1;

    private static List<Sala> salasDisponiveis;
    private static List<Professor> professoresDisponiveis;
    private static List<Turma> turmasCriadas;

    private static INotificacaoStrategy estrategiaEmail;
    private static INotificacaoStrategy estrategiaSms;
    private static IAlocacaoStrategy estrategiaPadrao;
    private static IAlocacaoStrategy estrategiaPrioritaria;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        exibirBanner();

        System.out.println("Escolha o que deseja ver:\n");
        System.out.println("  1. 💥 Parte 1: O CAOS (Código Legado com violações SOLID)");
        System.out.println("  2. ✨ Parte 2: A ORDEM (Código Refatorado com SOLID + Strategy)");
        System.out.println("  0. Sair\n");
        System.out.print("Opção: ");

        int escolhaInicial = lerInteiro(scanner);

        switch (escolhaInicial) {
            case 1:
                SistemaLegado.demonstrarCaos();
                System.out.println("\n[Pressione ENTER para voltar ao menu principal]");
                scanner.nextLine();
                main(args);
                break;
            case 2:
                executarSistemaRefatorado(scanner);
                break;
            case 0:
                System.out.println("\nEncerrando o sistema. Até logo! 👋");
                break;
            default:
                System.out.println("Opção inválida!");
                main(args);
                break;
        }

        scanner.close();
    }

    private static void executarSistemaRefatorado(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║       PARTE 2: A ORDEM — Sistema Refatorado             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        IAlunoRepository repositorio = new AlunoRepositoryMemoria();

        estrategiaEmail = new NotificacaoEmailStrategy();
        estrategiaSms = new NotificacaoSmsStrategy();
        estrategiaPadrao = new AlocacaoPadraoStrategy();
        estrategiaPrioritaria = new AlocacaoPrioritariaStrategy();

        GestaoAcademicaService servico = new GestaoAcademicaService(
                repositorio,
                estrategiaEmail,
                estrategiaPadrao
        );

        System.out.println("  ✔ Dependências injetadas com sucesso!");
        System.out.println("  ✔ Estratégia de Notificação: " + servico.getNomeEstrategiaNotificacao());
        System.out.println("  ✔ Estratégia de Alocação: " + servico.getNomeEstrategiaAlocacao());

        inicializarDados();

        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal(servico);
            int opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    menuMatricularAluno(scanner, servico);
                    break;
                case 2:
                    menuAlocarSala(scanner, servico);
                    break;
                case 3:
                    menuNotificarProfessor(scanner, servico);
                    break;
                case 4:
                    menuListarAlunos(servico);
                    break;
                case 5:
                    menuTrocarEstrategiaNotificacao(scanner, servico);
                    break;
                case 6:
                    menuTrocarEstrategiaAlocacao(scanner, servico);
                    break;
                case 7:
                    exibirInfoProfessores();
                    break;
                case 0:
                    executando = false;
                    System.out.println("\n  Encerrando o sistema refatorado. Até logo! 👋\n");
                    break;
                default:
                    System.out.println("\n  ⚠ Opção inválida! Tente novamente.\n");
                    break;
            }
        }
    }

    private static void menuMatricularAluno(Scanner scanner, GestaoAcademicaService servico) {
        System.out.println("\n── Matricular Aluno ──────────────────────────────────────");

        System.out.print("  Nome do aluno: ");
        String nomeAluno = scanner.nextLine().trim();
        if (nomeAluno.isEmpty()) {
            System.out.println("  ⚠ Nome não pode ser vazio!\n");
            return;
        }

        System.out.println("\n  Turmas disponíveis:");
        for (int i = 0; i < turmasCriadas.size(); i++) {
            Turma t = turmasCriadas.get(i);
            System.out.println("    " + (i + 1) + ". " + t.getNome()
                    + " (Prof. " + t.getProfessor().getNome() + " - " + t.getProfessor().getTipo() + ")");
        }
        System.out.print("  Escolha a turma (número): ");
        int indiceTurma = lerInteiro(scanner) - 1;

        if (indiceTurma < 0 || indiceTurma >= turmasCriadas.size()) {
            System.out.println("  ⚠ Turma inválida!\n");
            return;
        }

        Aluno novoAluno = new Aluno(proximoIdAluno++, nomeAluno);
        Turma turmaSelecionada = turmasCriadas.get(indiceTurma);

        servico.matricularAluno(novoAluno, turmaSelecionada);
        System.out.println();
    }

    private static void menuAlocarSala(Scanner scanner, GestaoAcademicaService servico) {
        System.out.println("\n── Alocar Sala para Turma ────────────────────────────────");

        System.out.println("\n  Turmas disponíveis:");
        for (int i = 0; i < turmasCriadas.size(); i++) {
            Turma t = turmasCriadas.get(i);
            String salaAtual = (t.getSala() != null) ? "Sala " + t.getSala().getNumero() : "Sem sala";
            System.out.println("    " + (i + 1) + ". " + t.getNome()
                    + " (" + t.getQuantidadeAlunos() + " alunos, " + salaAtual + ")");
        }
        System.out.print("  Escolha a turma (número): ");
        int indiceTurma = lerInteiro(scanner) - 1;

        if (indiceTurma < 0 || indiceTurma >= turmasCriadas.size()) {
            System.out.println("  ⚠ Turma inválida!\n");
            return;
        }

        System.out.println("\n  Salas disponíveis:");
        for (Sala s : salasDisponiveis) {
            System.out.println("    → Sala " + s.getNumero() + " (capacidade: " + s.getCapacidade() + ")");
        }

        Turma turmaSelecionada = turmasCriadas.get(indiceTurma);
        servico.alocarSalaTurma(turmaSelecionada, salasDisponiveis);
        System.out.println();
    }

    private static void menuNotificarProfessor(Scanner scanner, GestaoAcademicaService servico) {
        System.out.println("\n── Notificar Professor ───────────────────────────────────");

        System.out.println("\n  Professores disponíveis:");
        for (int i = 0; i < professoresDisponiveis.size(); i++) {
            Professor p = professoresDisponiveis.get(i);
            System.out.println("    " + (i + 1) + ". " + p.getNome()
                    + " (" + p.getTipo() + ", carga: " + p.getCargaHorariaMaxima() + "h)");
        }
        System.out.print("  Escolha o professor (número): ");
        int indiceProfessor = lerInteiro(scanner) - 1;

        if (indiceProfessor < 0 || indiceProfessor >= professoresDisponiveis.size()) {
            System.out.println("  ⚠ Professor inválido!\n");
            return;
        }

        System.out.print("  Mensagem: ");
        String mensagem = scanner.nextLine().trim();
        if (mensagem.isEmpty()) {
            System.out.println("  ⚠ Mensagem não pode ser vazia!\n");
            return;
        }

        Professor profSelecionado = professoresDisponiveis.get(indiceProfessor);
        servico.notificarProfessor(profSelecionado, mensagem);
        System.out.println();
    }

    private static void menuListarAlunos(GestaoAcademicaService servico) {
        System.out.println("\n── Lista de Alunos ───────────────────────────────────────");

        List<Aluno> alunos = servico.listarAlunos();

        if (alunos.isEmpty()) {
            System.out.println("  Nenhum aluno matriculado ainda.\n");
            return;
        }

        System.out.println("  Total: " + alunos.size() + " aluno(s)\n");
        for (Aluno aluno : alunos) {
            System.out.println("  → " + aluno);
        }
        System.out.println();
    }

    private static void menuTrocarEstrategiaNotificacao(Scanner scanner, GestaoAcademicaService servico) {
        System.out.println("\n── Trocar Estratégia de Notificação ──────────────────────");
        System.out.println("  Estratégia atual: " + servico.getNomeEstrategiaNotificacao());
        System.out.println("\n  1. ✉ Email (NotificacaoEmailStrategy)");
        System.out.println("  2. 📱 SMS (NotificacaoSmsStrategy)");
        System.out.print("\n  Escolha: ");

        int opcao = lerInteiro(scanner);

        switch (opcao) {
            case 1:
                servico.setNotificacaoStrategy(estrategiaEmail);
                break;
            case 2:
                servico.setNotificacaoStrategy(estrategiaSms);
                break;
            default:
                System.out.println("  ⚠ Opção inválida!");
                break;
        }
        System.out.println();
    }

    private static void menuTrocarEstrategiaAlocacao(Scanner scanner, GestaoAcademicaService servico) {
        System.out.println("\n── Trocar Estratégia de Alocação ─────────────────────────");
        System.out.println("  Estratégia atual: " + servico.getNomeEstrategiaAlocacao());
        System.out.println("\n  1. 📋 Padrão (primeira sala disponível)");
        System.out.println("  2. 🎯 Prioritária (menor sala que comporte a turma)");
        System.out.print("\n  Escolha: ");

        int opcao = lerInteiro(scanner);

        switch (opcao) {
            case 1:
                servico.setAlocacaoStrategy(estrategiaPadrao);
                break;
            case 2:
                servico.setAlocacaoStrategy(estrategiaPrioritaria);
                break;
            default:
                System.out.println("  ⚠ Opção inválida!");
                break;
        }
        System.out.println();
    }

    private static void exibirInfoProfessores() {
        System.out.println("\n── Informações dos Professores ───────────────────────────");
        System.out.println("  Demonstração do OCP e LSP refatorados:\n");

        for (Professor p : professoresDisponiveis) {
            System.out.println("  → " + p);
            System.out.println("    Classe: " + p.getClass().getSimpleName());
            System.out.println("    Carga Horária Máxima: " + p.getCargaHorariaMaxima() + "h");
            System.out.println();
        }

        System.out.println("  ✔ Cada tipo de professor é uma SUBCLASSE, não um if/else!");
        System.out.println("  ✔ Para adicionar um novo tipo, basta criar uma nova classe.\n");
    }

    private static void inicializarDados() {
        salasDisponiveis = new ArrayList<>();
        salasDisponiveis.add(new Sala(1, 101, 15));
        salasDisponiveis.add(new Sala(2, 202, 30));
        salasDisponiveis.add(new Sala(3, 303, 45));
        salasDisponiveis.add(new Sala(4, 404, 60));

        professoresDisponiveis = new ArrayList<>();
        professoresDisponiveis.add(new ProfessorWeg(1, "Carlos Silva"));
        professoresDisponiveis.add(new ProfessorSenai(2, "Maria Souza"));
        professoresDisponiveis.add(new ProfessorWeg(3, "João Oliveira"));
        professoresDisponiveis.add(new ProfessorSenai(4, "Fernanda Lima"));

        turmasCriadas = new ArrayList<>();
        turmasCriadas.add(new Turma(proximoIdTurma++, "Arquitetura de Software", professoresDisponiveis.get(0)));
        turmasCriadas.add(new Turma(proximoIdTurma++, "Banco de Dados", professoresDisponiveis.get(1)));
        turmasCriadas.add(new Turma(proximoIdTurma++, "Programação Web", professoresDisponiveis.get(2)));
        turmasCriadas.add(new Turma(proximoIdTurma++, "Redes de Computadores", professoresDisponiveis.get(3)));
    }

    private static void exibirBanner() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                          ║");
        System.out.println("║            🏛  GESTÃO CTW — Sistema Acadêmico            ║");
        System.out.println("║                                                          ║");
        System.out.println("║    Arquitetura de Software & Excelência Acadêmica        ║");
        System.out.println("║    Desafio: Da Ordem ao Caos (SOLID + Strategy)          ║");
        System.out.println("║                                                          ║");
        System.out.println("║    Desenvolvido por:                                     ║");
        System.out.println("║      Emanuelle Cristina Hostin                           ║");
        System.out.println("║      Ana Beatriz de Oliveira Ribeiro                     ║");
        System.out.println("║                                                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
    }

    private static void exibirMenuPrincipal(GestaoAcademicaService servico) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║            GESTÃO CTW — Menu Principal                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  1. 📝 Matricular Aluno                                  ║");
        System.out.println("║  2. 🏫 Alocar Sala para Turma                            ║");
        System.out.println("║  3. 📨 Notificar Professor                               ║");
        System.out.println("║  4. 📋 Listar Alunos                                     ║");
        System.out.println("║  5. 🔄 Trocar Estratégia de Notificação                  ║");
        System.out.println("║  6. 🔄 Trocar Estratégia de Alocação                     ║");
        System.out.println("║  7. 👨‍🏫 Ver Informações dos Professores                   ║");
        System.out.println("║  0. 🚪 Sair                                              ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  Notificação: " + padRight(servico.getNomeEstrategiaNotificacao(), 40) + " ║");
        System.out.println("║  Alocação:    " + padRight(servico.getNomeEstrategiaAlocacao(), 40) + " ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.print("  Opção: ");
    }

    private static int lerInteiro(Scanner scanner) {
        String linha = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String padRight(String texto, int tamanho) {
        if (texto.length() >= tamanho) {
            return texto.substring(0, tamanho);
        }
        StringBuilder sb = new StringBuilder(texto);
        while (sb.length() < tamanho) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
