package org.example.repository.Filme;

import java.sql.SQLException;
import java.util.List;

import org.example.model.Filme;

public interface FilmeRepository {

    void alterarStatus(Integer id) throws SQLException;

    List<Filme>buscarFilmes(List<Integer> ids) throws SQLException;
}
