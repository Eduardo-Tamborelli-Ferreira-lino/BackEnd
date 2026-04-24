package com.weg.ctw.infra;

import com.weg.ctw.domain.Aluno;
import com.weg.ctw.domain.IAlunoRepository;

import java.util.ArrayList;
import java.util.List;

public class AlunoRepositoryMemoria implements IAlunoRepository {

    private final List<Aluno> alunos = new ArrayList<>();

    @Override
    public void salvar(Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId() == aluno.getId()) {
                alunos.set(i, aluno);
                System.out.println("    [Repositório] Aluno atualizado: " + aluno.getNome());
                return;
            }
        }
        alunos.add(aluno);
        System.out.println("    [Repositório] Aluno salvo: " + aluno.getNome());
    }

    @Override
    public Aluno buscarPorId(int id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                return aluno;
            }
        }
        return null;
    }

    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }

    @Override
    public boolean remover(int id) {
        return alunos.removeIf(aluno -> aluno.getId() == id);
    }
}
