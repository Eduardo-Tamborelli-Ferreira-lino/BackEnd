package com.example.Repository.Airport;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.DataBase.Conexao;
import com.example.Model.Airport;

public class AirportRepositoryImpl implements AirportRepository {

    @Override
    public Airport sava(Airport airport) throws SQLException {

        String command = """
                INSERT INTO Aeroporto(
                nome,
                codigo_iata
                )
                VALUES
                (?, ?)
                """;

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, airport.getName());
            stmt.setString(2, airport.getIataCode());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                airport.setId(rs.getLong(1));
                return airport;
            }
            throw new RuntimeException("Error while registering.");
        }
    }

    @Override
    public Airport findById(long chosenId) throws SQLException {

        Airport airport;

        String command = """
                SELECT
                id,
                nome,
                codigo_iata
                FROM Aeroporto
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, chosenId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                airport = new Airport(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("codigo_iata"));
                return airport;
            }
            throw new RuntimeException("ID not found");
        }
    }

}
