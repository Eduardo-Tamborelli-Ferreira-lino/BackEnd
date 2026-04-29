package org.example.repository.Emprestimo;

import java.sql.SQLException;

import org.example.model.Emprestimo;

public interface EmprestimoRepository {

    Emprestimo buscarLivroId(Integer id) throws SQLException;

    Emprestimo atualizarEmprestimoDataDevolucao (Emprestimo emprestimo) throws SQLException;

    Emprestimo atualizarEmprestimoLeitorID (Emprestimo emprestimo) throws SQLException;

    Boolean buscarEmprestimoLeitor(Integer id) throws SQLException;
}
