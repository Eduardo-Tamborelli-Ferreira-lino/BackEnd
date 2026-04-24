package com.weg.ctw.infra;

import com.weg.ctw.domain.IAlocacaoStrategy;
import com.weg.ctw.domain.Sala;
import com.weg.ctw.domain.Turma;

import java.util.List;

public class AlocacaoPrioritariaStrategy implements IAlocacaoStrategy {

    @Override
    public Sala alocar(Turma turma, List<Sala> salasDisponiveis) {
        System.out.println("    [Alocação Prioritária] Buscando sala otimizada (menor capacidade suficiente)...");

        int quantidadeAlunos = turma.getQuantidadeAlunos();
        Sala melhorSala = null;

        for (Sala sala : salasDisponiveis) {
            if (sala.getCapacidade() >= quantidadeAlunos) {
                if (melhorSala == null || sala.getCapacidade() < melhorSala.getCapacidade()) {
                    melhorSala = sala;
                }
            }
        }

        if (melhorSala != null) {
            System.out.println("    [Alocação Prioritária] Sala " + melhorSala.getNumero()
                    + " selecionada (capacidade: " + melhorSala.getCapacidade()
                    + ", alunos: " + quantidadeAlunos + ")");
        }

        return melhorSala;
    }
}
