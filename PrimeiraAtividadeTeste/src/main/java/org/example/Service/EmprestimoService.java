package org.example.Service;

import java.sql.SQLException;

public interface EmprestimoService {
    boolean registrarEmprestimo (int livroId, int usuarioId) throws SQLException;

    boolean registrarDevolucao (int emprestimoId, int livroId) throws SQLException;
}
