package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3356/";
    private static final String DB_NAME = "cinema_db";
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
            conn.createStatement().executeUpdate("DROP DATABASE IF EXISTS cinema_db");
            conn.createStatement().executeUpdate("CREATE DATABASE cinema_db");
            
            try (Connection dbConn = getConnection()) {
                dbConn.createStatement().executeUpdate(
                    "CREATE TABLE Cliente (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) UNIQUE NOT NULL," +
                    "ativo BOOLEAN DEFAULT TRUE" +
                    ")"
                );
                
                dbConn.createStatement().executeUpdate(
                    "CREATE TABLE Filme (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "titulo VARCHAR(150) NOT NULL," +
                    "genero VARCHAR(50) NOT NULL," +
                    "status VARCHAR(20) NOT NULL" +
                    ")"
                );
                
                dbConn.createStatement().executeUpdate(
                    "CREATE TABLE Reserva (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "cliente_id INT NOT NULL," +
                    "filme_id INT NOT NULL," +
                    "data_reserva DATE NOT NULL," +
                    "data_sessao DATE NOT NULL," +
                    "FOREIGN KEY (cliente_id) REFERENCES Cliente(id)," +
                    "FOREIGN KEY (filme_id) REFERENCES Filme(id)" +
                    ")"
                );
                
                dbConn.createStatement().executeUpdate(
                    "CREATE TABLE Cancelamento (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "reserva_id INT NOT NULL," +
                    "motivo VARCHAR(255) NOT NULL," +
                    "taxa_multa DECIMAL(10,2) NOT NULL," +
                    "FOREIGN KEY (reserva_id) REFERENCES Reserva(id)" +
                    ")"
                );
            }
        }
    }
}
