package com.example.Repository.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.example.DataBase.Conexao;
import com.example.Model.Flight;

public class FlightRepositoryImpl implements FlightRepository {

    @Override
    public Flight save(Flight flight) throws SQLException {

        String command = """
                INSERT INTO Voo (
                aviao_id,
                origim_id,
                destino_id,
                horario
                )
                VALUES
                (?, ?, ?, ?)
                """;

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, flight.getPlaneId());
            stmt.setLong(2, flight.getOriginId());
            stmt.setLong(3, flight.getDestinationId());
            stmt.setObject(4, flight.getTime());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                flight.setId(rs.getLong(1));
                return flight;
            }
            throw new RuntimeException("Flight don't save");
        }
    }

    @Override
    public Flight findById(int chosenId) throws SQLException {

        Flight flight;

        String command = """
                SELECT
                id,
                aviao_id,
                origim_id,
                destino_id,
                horario
                FROM Voo
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, chosenId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return flight = new Flight(
                        rs.getLong("id"),
                        rs.getLong("aviao_id"),
                        rs.getLong("origem_id"),
                        rs.getLong("destino_id"),
                        rs.getObject("horario", LocalDate.class));
            }
            throw new RuntimeException("Some ID not found");
        }
    }

    @Override
    public void update(Flight flight) throws SQLException {

        String command = """
                UPDATE Voo
                set destino_id = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, flight.getDestinationId());
            stmt.setLong(2, flight.getId());

            int changeLines = stmt.executeUpdate();

            if (changeLines <= 0) {
                throw new RuntimeException("ID not found");
            }
        }
    }

    @Override
    public void delete(int chosenId) throws SQLException {

        String command = """
                DELETE FROM Voo
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, chosenId);

            int changeLines = stmt.executeUpdate();

            if (changeLines <= 0) {
                throw new RuntimeException("ID not found");
            }
        }
    }

}
