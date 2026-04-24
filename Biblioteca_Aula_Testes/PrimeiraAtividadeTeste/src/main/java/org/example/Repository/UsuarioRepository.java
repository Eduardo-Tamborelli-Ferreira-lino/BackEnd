package org.example.Repository;

import org.example.Connection.Conexao;
import org.example.Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioRepository {

    public void inserirUsuario(Usuario usuario) throws SQLException {
        String command = """
                INSERT INTO Usuario(
                nome,
                email)
                VALUES
                (?, ?)
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
        }
    }

    public ArrayList<Usuario> consultarUsuarios() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String command = """
                SELECT
                id,
                nome,
                email
                FROM Usuario
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email")));
            }
        }
        return usuarios;
    }

    public Usuario buscarUsuario(int usuarioId) throws SQLException {
        Usuario usuario = null;
        String command = """
                SELECT
                id,
                nome,
                email
                FROM Usuario
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"));
            }
        }
        return usuario;
    }
}
