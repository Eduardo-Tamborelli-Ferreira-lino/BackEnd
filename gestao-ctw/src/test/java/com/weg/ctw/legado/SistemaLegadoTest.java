package com.weg.ctw.legado;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaLegadoTest {

    @Test
    @DisplayName("LEGADO: SRP — classe faz cadastro E notificação ao mesmo tempo")
    void srpClasseFazTudo() {
        SistemaLegado sistema = new SistemaLegado();

        assertDoesNotThrow(() -> sistema.cadastrarAluno("Ana", 101));

        String relatorio = sistema.gerarRelatorio();
        assertTrue(relatorio.contains("Alunos cadastrados: 1"));
        assertTrue(relatorio.contains("Notificações enviadas: 1"));
    }

    @Test
    @DisplayName("LEGADO: OCP — cálculo de carga horária depende de if/else")
    void ocpCalculoCargaHoraria() {
        SistemaLegado sistema = new SistemaLegado();

        assertEquals(20.0, sistema.calcularCargaHoraria("WEG"));
        assertEquals(40.0, sistema.calcularCargaHoraria("SENAI"));
        assertEquals(10.0, sistema.calcularCargaHoraria("TEMPORARIO"));
        assertEquals(0.0, sistema.calcularCargaHoraria("DESCONHECIDO"));
    }

    @Test
    @DisplayName("LEGADO: LSP — ProfessorTemporario lança exceção em alocarSala()")
    void lspProfessorTemporarioQuebraContrato() {
        SistemaLegado.Professor profNormal = new SistemaLegado.Professor("Carlos", "SENAI");

        assertDoesNotThrow(() -> profNormal.alocarSala(5));

        SistemaLegado.Professor profTemp = new SistemaLegado.ProfessorTemporario("Maria");
        assertThrows(UnsupportedOperationException.class, () -> profTemp.alocarSala(5));
    }

    @Test
    @DisplayName("LEGADO: OCP — notificação depende de switch/case")
    void ocpNotificacaoDependeDeSwitch() {
        SistemaLegado sistema = new SistemaLegado();

        assertDoesNotThrow(() -> sistema.enviarNotificacao("user@email.com", "teste", "EMAIL"));
        assertDoesNotThrow(() -> sistema.enviarNotificacao("48999887766", "teste", "SMS"));
        assertDoesNotThrow(() -> sistema.enviarNotificacao("user", "teste", "WHATSAPP"));

        String relatorio = sistema.gerarRelatorio();
        assertEquals(2, contarOcorrencias(relatorio, "→"));
    }

    @Test
    @DisplayName("LEGADO: demonstrarCaos() executa sem exceções não tratadas")
    void demonstrarCaosExecutaSemErro() {
        assertDoesNotThrow(SistemaLegado::demonstrarCaos);
    }

    private int contarOcorrencias(String texto, String substring) {
        int count = 0;
        int idx = 0;
        while ((idx = texto.indexOf(substring, idx)) != -1) {
            count++;
            idx += substring.length();
        }
        return count;
    }
}
