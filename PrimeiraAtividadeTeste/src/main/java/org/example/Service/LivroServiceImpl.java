package org.example.Service;

import org.example.Model.Livro;
import org.example.Repository.LivroRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class LivroServiceImpl implements LivroService {
    private LivroRepositoryImpl livroRepo = new LivroRepositoryImpl();

    public Livro cadastrarLivro (Livro livro) throws SQLException {
        if (livro.getTitulo() != null && !livro.getTitulo().isBlank() &&
                livro.getAutor() != null && !livro.getAutor().isBlank()
                && livro.getAno() > 0 ) {
            boolean disponivel = true;
            livro.setDisponivel(disponivel);
            livroRepo.inserirLivro(livro);
            return livro;
        }
        else{
            return null ;
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

    public Livro atualizarStatus (boolean disponivel, Livro livro) throws SQLException{
        livroRepo.atualizarStatus(disponivel, livro.getId());
        livro.setDisponivel(disponivel);
        return livro;
    }
}
