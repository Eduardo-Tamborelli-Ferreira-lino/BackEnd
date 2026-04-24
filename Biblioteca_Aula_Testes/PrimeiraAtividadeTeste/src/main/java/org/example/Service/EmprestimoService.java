package org.example.Service;

import org.example.Model.Emprestimo;
import org.example.Model.Livro;
import org.example.Model.Usuario;
import org.example.Repository.EmprestimoRepository;
import org.example.Repository.LivroRepository;

import java.sql.SQLException;
import java.time.LocalDate;

public class EmprestimoService {

    private final UsuarioService usuarioSer = new UsuarioService();
    private final LivroService livroSer = new LivroService();
    private final EmprestimoRepository emprestimoRepo = new EmprestimoRepository();
    private final LivroRepository livroRepo = new LivroRepository();

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
