package com.weg.minha_primeira_api.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3356/";
    private static final String DB_NAME = "contatos";
    private static final String USER = "root";
    private static final String PASSWORD = "mysqlPW"; 
    private static final String FINALURL = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL + DB_NAME + FINALURL, USER, PASSWORD);
    }

    public static Connection getConnectionWithoutDB() throws SQLException {
        return DriverManager.getConnection(URL + FINALURL, USER, PASSWORD);
    }

    public static void initializeDatabase() throws SQLException {
        try (Connection conn = getConnectionWithoutDB()) {
            conn.createStatement().executeUpdate("DROP DATABASE IF EXISTS contatos");
            conn.createStatement().executeUpdate("CREATE DATABASE contatos");
            
            try (Connection dbConn = getConnection()) {
                dbConn.createStatement().executeUpdate(
                    "CREATE TABLE Contato (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "numero VARCHAR(100) UNIQUE NOT NULL" +
                    ")"
                );
            }
        }
    }
}
