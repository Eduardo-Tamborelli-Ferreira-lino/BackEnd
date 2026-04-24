package com.weg.ctw.domain;

import com.weg.ctw.dto.AlunoDTO;
import com.weg.ctw.dto.TurmaDTO;

public class DtoMapper {

    public static AlunoDTO toAlunoDTO(Aluno aluno) {
        String nomeTurma = (aluno.getTurma() != null) ? aluno.getTurma().getNome() : "Sem turma";
        return new AlunoDTO(aluno.getId(), aluno.getNome(), nomeTurma);
    }

    public static TurmaDTO toTurmaDTO(Turma turma) {
        String salaAlocada = (turma.getSala() != null)
                ? "Sala " + turma.getSala().getNumero()
                : "Sem sala";

        return new TurmaDTO(
                turma.getId(),
                turma.getNome(),
                turma.getProfessor().getNome(),
                turma.getProfessor().getTipo(),
                turma.getQuantidadeAlunos(),
                salaAlocada
        );
    }
}
