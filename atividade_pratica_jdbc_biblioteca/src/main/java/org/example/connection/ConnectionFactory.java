package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3356/";
    private static final String DB_NAME = "biblioteca";
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
            conn.createStatement().executeUpdate("DROP DATABASE IF EXISTS biblioteca");
            conn.createStatement().executeUpdate("CREATE DATABASE biblioteca");
            
            try (Connection dbConn = getConnection()) {
                dbConn.createStatement().executeUpdate(
                    "CREATE TABLE Leitor (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) UNIQUE NOT NULL," +
                    "ativo BOOLEAN DEFAULT TRUE" +
                    ")"
                );
                
                dbConn.createStatement().executeUpdate(
                    "CREATE TABLE Livro (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "titulo VARCHAR(150) NOT NULL," +
                    "isbn VARCHAR(20) UNIQUE NOT NULL," +
                    "status VARCHAR(20) NOT NULL" +
                    ")"
                );
                
                dbConn.createStatement().executeUpdate(
                    "CREATE TABLE Emprestimo (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "leitor_id INT NOT NULL," +
                    "livro_id INT NOT NULL," +
                    "data_emprestimo DATE NOT NULL," +
                    "data_devolucao DATE," +
                    "FOREIGN KEY (leitor_id) REFERENCES Leitor(id)," +
                    "FOREIGN KEY (livro_id) REFERENCES Livro(id)" +
                    ")"
                );
                
                dbConn.createStatement().executeUpdate(
                    "CREATE TABLE Infracao (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "emprestimo_id INT NOT NULL," +
                    "descricao VARCHAR(255) NOT NULL," +
                    "gravidade VARCHAR(20) NOT NULL," +
                    "FOREIGN KEY (emprestimo_id) REFERENCES Emprestimo(id)" +
                    ")"
                );
            }
        }
    }
}
