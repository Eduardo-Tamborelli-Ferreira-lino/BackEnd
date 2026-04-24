package org.example.Service;

import org.example.Model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioService {
    ArrayList<Usuario> listarUsuarios () throws SQLException;

    Usuario buscarUsuario (int usuarioId) throws SQLException;
}
