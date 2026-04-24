package com.weg.ctw.domain;

import java.util.List;

public interface IAlunoRepository {

    void salvar(Aluno aluno);

    Aluno buscarPorId(int id);

    List<Aluno> listarTodos();

    boolean remover(int id);
}
