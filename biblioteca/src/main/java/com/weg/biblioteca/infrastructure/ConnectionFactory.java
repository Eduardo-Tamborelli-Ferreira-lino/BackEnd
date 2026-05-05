package com.weg.biblioteca.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3356/";
    private static final String DB_NAME = "library";
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
            conn.createStatement().executeUpdate("DROP DATABASE IF EXISTS library");
            conn.createStatement().executeUpdate("CREATE DATABASE library");
            
            try (Connection dbConn = getConnection()) {
                dbConn.createStatement().executeUpdate(
                    "CREATE TABLE Book (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "title VARCHAR(150) NOT NULL," +
                    "author VARCHAR(100) UNIQUE NOT NULL," +
                    "year_publication INT NOT NULL" +
                    ")"
                );

                dbConn.createStatement().executeUpdate(
                    """
                    CREATE TABLE User (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL
                    );
                    """
                );

                dbConn.createStatement().executeUpdate(
                    """
                    CREATE TABLE Loan (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    book_id BIGINT NOT NULL,
                    user_id BIGINT NOT NULL,
                    loan_date DATE NOT NULL,
                    return_date DATE,
                    FOREIGN KEY (book_id) REFERENCES book(id),
                    FOREIGN KEY (user_id) REFERENCES user(id)
                    );
                    """
                );


            }
        }
    }
}
