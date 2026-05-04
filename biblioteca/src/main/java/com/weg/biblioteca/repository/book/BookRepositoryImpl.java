package com.weg.biblioteca.repository.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.biblioteca.infrastructure.ConnectionFactory;
import com.weg.biblioteca.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository{

    @Override
    public Book save(Book book) throws SQLException {

        String command = """
                INSERT INTO Book (
                title,
                author,
                year_publication)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)){
            
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYearPublication());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                Long generatedId = rs.getLong(1);

                book.setId(generatedId);

                return book;
            }

            throw new RuntimeException("Can't register the book.");
        }
    }

    @Override
    public Book findById(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Book> findAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Boolean bookExist() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bookExist'");
    }

    @Override
    public void update() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
