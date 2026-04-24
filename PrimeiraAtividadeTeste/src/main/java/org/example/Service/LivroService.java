package org.example.Service;

import org.example.Model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LivroService {
    Livro cadastrarLivro (Livro livro) throws SQLException;

    ArrayList<Livro> consultarLivros () throws SQLException;

    Livro buscarLivro (int livroId) throws SQLException;
}
