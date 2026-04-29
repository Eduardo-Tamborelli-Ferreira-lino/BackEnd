package org.example.service;

import org.example.model.*;

import java.sql.SQLException;
import java.util.List;

public interface LibraryService {
    Infracao registrarInfracao(Infracao infracao) throws SQLException;
    Emprestimo atualizarEmprestimo(Emprestimo emprestimo) throws SQLException;
    void excluirLeitor(Integer id) throws SQLException;
    List<RelatorioInfracao> gerarRelatorioInfracoes() throws SQLException;
    List<Livro> buscarLivrosPorIds(List<Integer> ids) throws SQLException;
    List<Leitor> buscarLeitoresPorNome(String nome) throws SQLException;
}
