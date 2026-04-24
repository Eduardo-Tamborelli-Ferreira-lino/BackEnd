package org.example.Repository;

import org.example.Model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioRepository {
    void inserirUsuario (Usuario usuario) throws SQLException;

    ArrayList<Usuario> consultarUsuarios () throws SQLException;

    Usuario buscarUsuario (int usuarioId) throws SQLException;
}
