package org.example.repository.Leitor;

import java.sql.SQLException;
import java.util.List;

import org.example.model.Leitor;

public interface LeitorRepository {

    void excluirLeitor (Integer id ) throws SQLException;

    List<Leitor> buscarLeitorPorNome (String nome) throws SQLException;
}
