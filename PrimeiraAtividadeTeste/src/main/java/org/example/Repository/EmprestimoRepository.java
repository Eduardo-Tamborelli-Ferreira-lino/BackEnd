package org.example.Repository;

import org.example.Model.Emprestimo;

import java.sql.SQLException;
import java.time.LocalDate;

public interface EmprestimoRepository {
    Emprestimo registrarEmprestimo (Emprestimo emprestimo) throws SQLException;

    void registrarDevolucao (LocalDate dataDevolucao, int emprestimoId ) throws SQLException;
}
