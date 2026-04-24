package org.example.Service;

import org.example.Model.Livro;
import org.example.Model.Usuario;
import org.example.Repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioService {
    private UsuarioRepository usuarioRepo = new UsuarioRepository();

    public ArrayList <Usuario> listarUsuarios () throws SQLException {
        ArrayList <Usuario> usuarios = new ArrayList<>();
        usuarios = usuarioRepo.consultarUsuarios();
        if (usuarios == null || usuarios.isEmpty()){
            return null;
        }
        return usuarios;
    }

    public Usuario buscarUsuario (int usuarioId) throws SQLException {
        Usuario usuario = null;
        usuario = usuarioRepo.buscarUsuario(usuarioId);
        if (usuario == null){
            return null;
        }
        return usuario;
    }
}
