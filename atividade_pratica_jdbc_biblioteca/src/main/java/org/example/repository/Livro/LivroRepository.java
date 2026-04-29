package org.example.repository.Livro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.model.Livro;

public interface LivroRepository {

    void alterarStatus (Integer id) throws SQLException;

    ArrayList<Livro> buscarLivros(List<Integer>id) throws SQLException;
}
