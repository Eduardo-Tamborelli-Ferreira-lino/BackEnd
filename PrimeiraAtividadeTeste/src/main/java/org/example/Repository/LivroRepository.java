package org.example.Repository;

import org.example.Model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LivroRepository {
    Livro inserirLivro (Livro livro) throws SQLException;

    ArrayList<Livro> consultarLivros () throws SQLException;

    void atualizarStatus (Boolean disponivel, int livroId) throws SQLException;

    Livro buscarLivros (int livroId) throws SQLException;

}
