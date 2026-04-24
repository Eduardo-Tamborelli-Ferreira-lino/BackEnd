package com.weg.ctw.legado;

import java.util.ArrayList;
import java.util.List;

public class SistemaLegado {

    interface IGestaoCompleta {
        void cadastrarAluno(String nome, int turmaId);
        void alocarSala(int turmaId, int salaNumero);
        void enviarNotificacao(String destinatario, String mensagem, String tipo);
        double calcularCargaHoraria(String tipoProfessor);
        String gerarRelatorio();
    }

    static class Professor {
        String nome;
        String tipo;

        Professor(String nome, String tipo) {
            this.nome = nome;
            this.tipo = tipo;
        }

        void alocarSala(int salaNumero) {
            System.out.println("[LEGADO] Professor " + nome + " alocado na sala " + salaNumero);
        }
    }

    static class ProfessorTemporario extends Professor {
        ProfessorTemporario(String nome) {
            super(nome, "TEMPORARIO");
        }

        @Override
        void alocarSala(int salaNumero) {
            throw new UnsupportedOperationException(
                "ERRO: Professor temporário NÃO pode ser alocado em sala fixa!"
            );
        }
    }

    private List<String> bancoDeDadosAlunos = new ArrayList<>();
    private List<String> bancoDeDadosTurmas = new ArrayList<>();
    private List<String> logNotificacoes = new ArrayList<>();

    public double calcularCargaHoraria(String tipoProfessor) {
        if (tipoProfessor.equals("WEG")) {
            System.out.println("[LEGADO] Calculando carga horária para professor WEG...");
            return 20.0;
        } else if (tipoProfessor.equals("SENAI")) {
            System.out.println("[LEGADO] Calculando carga horária para professor SENAI...");
            return 40.0;
        } else if (tipoProfessor.equals("TEMPORARIO")) {
            System.out.println("[LEGADO] Calculando carga horária para professor TEMPORÁRIO...");
            return 10.0;
        } else {
            System.out.println("[LEGADO] Tipo de professor desconhecido!");
            return 0.0;
        }
    }

    public void cadastrarAluno(String nomeAluno, int turmaId) {
        String registro = "Aluno: " + nomeAluno + " | Turma: " + turmaId;
        bancoDeDadosAlunos.add(registro);
        System.out.println("[LEGADO] Aluno cadastrado diretamente no banco: " + registro);

        System.out.println("[LEGADO] Enviando email de confirmação para " + nomeAluno + "...");
        logNotificacoes.add("EMAIL enviado para " + nomeAluno);
    }

    public void enviarNotificacao(String destinatario, String mensagem, String tipoNotificacao) {
        switch (tipoNotificacao) {
            case "EMAIL":
                System.out.println("[LEGADO] ✉ Enviando EMAIL para " + destinatario + ": " + mensagem);
                logNotificacoes.add("EMAIL -> " + destinatario + ": " + mensagem);
                break;
            case "SMS":
                System.out.println("[LEGADO] 📱 Enviando SMS para " + destinatario + ": " + mensagem);
                logNotificacoes.add("SMS -> " + destinatario + ": " + mensagem);
                break;
            default:
                System.out.println("[LEGADO] ERRO: Tipo de notificação '" + tipoNotificacao + "' não suportado!");
                break;
        }
    }

    public void alocarSala(int turmaId, int salaNumero, String tipoProfessor) {
        if (tipoProfessor.equals("WEG")) {
            System.out.println("[LEGADO] Alocação WEG: Turma " + turmaId + " -> Sala " + salaNumero);
            System.out.println("[LEGADO] (Professor WEG tem preferência por salas com projetor)");
        } else if (tipoProfessor.equals("SENAI")) {
            System.out.println("[LEGADO] Alocação SENAI: Turma " + turmaId + " -> Sala " + salaNumero);
            System.out.println("[LEGADO] (Professor SENAI usa laboratório quando disponível)");
        } else {
            System.out.println("[LEGADO] Alocação genérica: Turma " + turmaId + " -> Sala " + salaNumero);
        }

        bancoDeDadosTurmas.add("Turma " + turmaId + " -> Sala " + salaNumero);
    }

    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n============================================\n");
        sb.append("   RELATÓRIO DO SISTEMA LEGADO\n");
        sb.append("============================================\n");
        sb.append("Alunos cadastrados: ").append(bancoDeDadosAlunos.size()).append("\n");
        for (String aluno : bancoDeDadosAlunos) {
            sb.append("  → ").append(aluno).append("\n");
        }
        sb.append("Turmas alocadas: ").append(bancoDeDadosTurmas.size()).append("\n");
        for (String turma : bancoDeDadosTurmas) {
            sb.append("  → ").append(turma).append("\n");
        }
        sb.append("Notificações enviadas: ").append(logNotificacoes.size()).append("\n");
        for (String notif : logNotificacoes) {
            sb.append("  → ").append(notif).append("\n");
        }
        sb.append("============================================\n");
        return sb.toString();
    }

    public static void demonstrarCaos() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║        PARTE 1: O CAOS — Sistema Legado Monolítico      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        SistemaLegado sistema = new SistemaLegado();

        sistema.cadastrarAluno("Ana Beatriz", 101);
        sistema.cadastrarAluno("Emanuelle Cristina", 102);
        System.out.println();

        sistema.calcularCargaHoraria("WEG");
        sistema.calcularCargaHoraria("SENAI");
        sistema.calcularCargaHoraria("TEMPORARIO");
        System.out.println();

        sistema.alocarSala(101, 5, "WEG");
        sistema.alocarSala(102, 12, "SENAI");
        System.out.println();

        sistema.enviarNotificacao("prof.silva@ctw.com", "Bem-vindo ao semestre!", "EMAIL");
        sistema.enviarNotificacao("48999887766", "Sua turma foi alocada.", "SMS");
        sistema.enviarNotificacao("prof.silva", "Teste WhatsApp", "WHATSAPP");
        System.out.println();

        Professor profNormal = new Professor("Carlos Silva", "SENAI");
        profNormal.alocarSala(5);

        Professor profTemp = new ProfessorTemporario("Maria Souza");
        try {
            profTemp.alocarSala(10);
        } catch (UnsupportedOperationException e) {
            System.out.println("[LEGADO] ❌ VIOLAÇÃO LSP: " + e.getMessage());
        }

        System.out.println(sistema.gerarRelatorio());

        System.out.println("[LEGADO] Observe: UMA classe faz TUDO. Isso é o CAOS.\n");
    }
}
