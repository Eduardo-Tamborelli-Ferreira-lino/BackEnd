package com.weg.ctw.domain;

import java.util.List;

public interface IAlocacaoStrategy {

    Sala alocar(Turma turma, List<Sala> salasDisponiveis);
}
