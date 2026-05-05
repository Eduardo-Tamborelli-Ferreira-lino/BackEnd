package com.weg.biblioteca.repository.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                year_publication
                )
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
    public Optional<Book> findById(Long id) throws SQLException {

        String command = """
                SELECT
                title,
                author,
                year_publication
                FROM Book
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                String foundTitle = rs.getString("title");
                String foundAuthor = rs.getString("author");
                int foundYear = rs.getInt("year_publication");

                return Optional.of(new Book(
                            id,
                            foundTitle,
                            foundAuthor,
                            foundYear
                ));
            }
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() throws SQLException {

        List<Book> books = new ArrayList<>();

        String command = """
                SELECT
                id,
                title,
                author,
                year_publication
                FROM Book
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String author = rs.getString("title");
                int yearPublication = rs.getInt("year_publication");
                
                books.add(new Book(
                    id,
                    title,
                    author,
                    yearPublication
                ));
            }
            return books;
        }
    }

    @Override
    public Boolean bookExist(Long id) throws SQLException {

        String command = """
                SELECT COUNT(0) as result
                FROM Book
                where id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                int result = rs.getInt("result");

                if (result == 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        throw new RuntimeException("This Book don't exist");
    }

    @Override
    public void update(Book book) throws SQLException {

        String command = """
                UPDATE Book
                SET 
                title = ?,
                author = ?,
                year_publication = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYearPublication());
            stmt.setLong(4, book.getId());

            int alterLines = stmt.executeUpdate();

            if (alterLines <= 0) {
                throw new RuntimeException("Failed on Update of the book");
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {

        String command = """
                DELETE FROM Book
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            int alterLines = stmt.executeUpdate();

            if (alterLines <= 0) {
                throw new RuntimeException("Failed on Delete of the Book");
            }
        }
    }

}
