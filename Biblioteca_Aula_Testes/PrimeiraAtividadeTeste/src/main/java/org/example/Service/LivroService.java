package org.example.Service;

import org.example.Model.Livro;
import org.example.Repository.LivroRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class LivroService {
    private LivroRepository livroRepo = new LivroRepository();

    public boolean cadastrarLivro (String titulo, String autor, int ano) throws SQLException {
        if (titulo != null && !titulo.isBlank() &&
                autor != null && !autor.isBlank()
                && ano > 0 ) {
            boolean disponivel = true;
            Livro livro = new Livro(titulo, autor, ano, disponivel);
            livroRepo.inserirLivro(livro);
            return true;
        }
        else{
            return false ;
        }
    }

    public ArrayList <Livro> consultarLivros () throws SQLException {
        ArrayList <Livro> livros = new ArrayList<>();
        livros = livroRepo.consultarLivros();
        if (livros == null || livros.isEmpty()){
            return null;
        }
        else {
            return livros;
        }
    }

    public Livro buscarLivro (int livroId) throws SQLException {
        Livro livro = null;
        livro = livroRepo.buscarLivros(livroId);
        if (livro == null){
            return null;
        }
        return livro;
    }
}
