package com.weg.minha_primeira_api.repository;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.weg.minha_primeira_api.connection.ConnectionFactory;
import com.weg.minha_primeira_api.model.Contato;

@Repository
public class ContatoRepositoryImpl implements ContatoRepository {

    @Override
    public Contato post(Contato contato) throws SQLException {

        String command = """
                INSERT INTO Contato (
                nome,
                numero)
                VALUES
                (?, ?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, contato.getNome());

            stmt.setString(2, contato.getNumero());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                contato.setId(rs.getLong(1));
                return contato;
            }
        }
        throw new RuntimeException("Credencias inválidas.");
    }

    @Override
    public Contato get(Long id) throws SQLException {

        Contato contato;

        String command = """
                SELECT
                id,
                nome,
                numero
                FROM Contato
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                contato = new Contato(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("numero"));
            } else {
                throw new RuntimeException("ID not found on data base");
            }
        }

        return contato;
    }

    @Override
    public void put(Contato contato) throws SQLException {

        String command = """
                UPDATE Contato
                SET 
                nome = ?,
                numero = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getNumero());
            stmt.setLong(3, contato.getId());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("ID not found on data base");
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {

        String command = """
                DELETE FROM Contato
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("Contact don't exist");
            }
        }
    }

    @Override
    public ArrayList<Contato> getAll() throws SQLException {

        ArrayList<Contato> contatos = new ArrayList<>();

        String command = """
                SELECT 
                id,
                nome,
                numero
                FROM Contato
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                contatos.add(new Contato(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getString("numero")
                ));
            }
            return contatos;
        }
    }

}
