package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3356/";
    private static final String DB_NAME = "estudo1";
    private static final String USER = "root";
    private static final String PASSWORD = "mysqlPW";
    private static final String FINALURL = "?useSSL=false&serverTimezone=UTC";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL + DB_NAME + FINALURL, USER, PASSWORD);
    }

    public static Connection getConnectionWithoutDB() throws SQLException {
        return DriverManager.getConnection(URL + FINALURL, USER, PASSWORD);
    }

    public static void initializeDatabase() throws SQLException {
        try (Connection conn = getConnectionWithoutDB()) {
            conn.createStatement().executeUpdate("DROP DATABASE IF EXISTS estudo1");
            conn.createStatement().executeUpdate("CREATE DATABASE estudo1");

            try (Connection dbConn = getConnection()) {
                dbConn.createStatement().executeUpdate(
                        "CREATE TABLE Motorista (" +
                                "id INT PRIMARY KEY AUTO_INCREMENT," +
                                "nome VARCHAR(100) NOT NULL," +
                                "cnh VARCHAR(20) UNIQUE NOT NULL," +
                                "ativo BOOLEAN DEFAULT TRUE" +
                                ")");

                dbConn.createStatement().executeUpdate(
                        "CREATE TABLE Veiculo (" +
                                "id INT PRIMARY KEY AUTO_INCREMENT," +
                                "placa VARCHAR(10) UNIQUE NOT NULL," +
                                "modelo VARCHAR(50) NOT NULL," +
                                "status VARCHAR(20) NOT NULL" +
                                ")");

                dbConn.createStatement().executeUpdate(
                        "CREATE TABLE Alocacao (" +
                                "id INT PRIMARY KEY AUTO_INCREMENT," +
                                "motorista_id INT NOT NULL," +
                                "veiculo_id INT NOT NULL," +
                                "data_inicio DATE NOT NULL," +
                                "data_fim DATE," +
                                "FOREIGN KEY (motorista_id) REFERENCES Motorista(id)," +
                                "FOREIGN KEY (veiculo_id) REFERENCES Veiculo(id)" +
                                ")");

                dbConn.createStatement().executeUpdate(
                        "CREATE TABLE Incidente (" +
                                "id INT PRIMARY KEY AUTO_INCREMENT," +
                                "alocacao_id INT NOT NULL," +
                                "descricao VARCHAR(255) NOT NULL," +
                                "gravidade VARCHAR(20) NOT NULL," +
                                "data_incidente DATE NOT NULL," +
                                "FOREIGN KEY (alocacao_id) REFERENCES Alocacao(id)" +
                                ")");
            }
        }
    }
}
