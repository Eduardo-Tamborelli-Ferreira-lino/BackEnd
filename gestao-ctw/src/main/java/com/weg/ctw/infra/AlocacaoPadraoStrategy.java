package com.weg.ctw.infra;

import com.weg.ctw.domain.IAlocacaoStrategy;
import com.weg.ctw.domain.Sala;
import com.weg.ctw.domain.Turma;

import java.util.List;

public class AlocacaoPadraoStrategy implements IAlocacaoStrategy {

    @Override
    public Sala alocar(Turma turma, List<Sala> salasDisponiveis) {
        System.out.println("    [Alocação Padrão] Buscando primeira sala disponível...");

        int quantidadeAlunos = turma.getQuantidadeAlunos();

        for (Sala sala : salasDisponiveis) {
            if (sala.getCapacidade() >= quantidadeAlunos) {
                System.out.println("    [Alocação Padrão] Sala " + sala.getNumero()
                        + " encontrada (capacidade: " + sala.getCapacidade() + ")");
                return sala;
            }
        }

        return null;
    }
}
