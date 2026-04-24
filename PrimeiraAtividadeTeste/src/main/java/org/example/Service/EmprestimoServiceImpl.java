package org.example.Service;

import org.example.Model.Emprestimo;
import org.example.Model.Livro;
import org.example.Model.Usuario;
import org.example.Repository.EmprestimoRepositoryImpl;
import org.example.Repository.LivroRepositoryImpl;

import java.sql.SQLException;
import java.time.LocalDate;

public class EmprestimoServiceImpl implements EmprestimoService{

    private final UsuarioServiceImpl usuarioSer = new UsuarioServiceImpl();
    private final LivroServiceImpl livroSer = new LivroServiceImpl();
    private final EmprestimoRepositoryImpl emprestimoRepo = new EmprestimoRepositoryImpl();
    private final LivroRepositoryImpl livroRepo = new LivroRepositoryImpl();

    public boolean registrarEmprestimo (int livroId, int usuarioId) throws SQLException {
        Livro livro;
        Usuario usuario;
        livro = livroSer.buscarLivro(livroId);
        usuario = usuarioSer.buscarUsuario(usuarioId);
        if (livro == null || usuario == null){
            return false;
        }
        if (!livro.isDisponivel()){
            return false;
        }
        LocalDate dataEmprestimo = LocalDate.now();
        boolean disponivel = false;
        Emprestimo emprestimo = new Emprestimo(livroId, usuarioId, dataEmprestimo);
        emprestimoRepo.registrarEmprestimo(emprestimo);
        livroRepo.atualizarStatus(disponivel, livroId);
        return true;
    }

    public boolean registrarDevolucao (int emprestimoId, int livroId) throws SQLException {
        LocalDate dataDevolucao = LocalDate.now();
        emprestimoRepo.registrarDevolucao(dataDevolucao, emprestimoId);
        livroRepo.atualizarStatus(true, livroId);
        return true;
    }


}
