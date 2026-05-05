package com.weg.biblioteca.repository.loan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.weg.biblioteca.infrastructure.ConnectionFactory;
import com.weg.biblioteca.model.Loan;

@Repository
public class LoanRepositoryImpl implements LoanRepository{

    @Override
    public Loan save(Loan loan) throws SQLException {

        String command = """
                INSERT INTO Loan(
                book_id,
                user_id,
                loan_date
                )
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, loan.getBook_id());
            stmt.setLong(2, loan.getUser_id());
            stmt.setDate(3, Date.valueOf(loan.getLoanDate()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                Long generatedId = rs.getLong(1);

                loan.setId(generatedId);

                return loan;
            }
        }
        throw new RuntimeException("Failed on Save the Loan");
    }

    @Override
    public Optional<Loan> findById(Long id) throws SQLException {

        String command = """
                SELECT
                book_id,
                user_id,
                loan_date,
                return_date
                FROM Loan
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                Long bookId = rs.getLong("book_id");
                Long userId = rs.getLong("user_id");
                LocalDate loanDate = rs.getDate("loan_date") != null ? rs.getDate("loan_date").toLocalDate() : null;
                LocalDate returnDate = rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null;

                return Optional.of(new Loan(
                    id,
                    bookId,
                    userId,
                    loanDate,
                    returnDate
                ));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Loan> findAll() throws SQLException {

        List<Loan> loans = new ArrayList<>();

        String command = """
                SELECT
                id,
                book_id,
                user_id,
                loan_date,
                return_date
                FROM Loan
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long bookId = rs.getLong("book_id");
                Long userId = rs.getLong("user_id");
                LocalDate loanDate = rs.getDate("loan_date") != null ? rs.getDate("loan_date").toLocalDate() : null;
                LocalDate returnDate = rs.getDate("return_date") != null ? rs.getDate("loan_date").toLocalDate() : null;

                loans.add(new Loan(
                    id,
                    bookId,
                    userId,
                    loanDate,
                    returnDate
                ));
            }
            return loans;
        }
    }

    @Override
    public Boolean loanExist(Long id) throws SQLException {

        String command  = """
                SELECT COUNT(0) as result
                FROM Loan
                WHERE id = ?
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
        throw new RuntimeException("Loan don't exist");
    }

    @Override
    public void update(Loan loan) throws SQLException {

        String command = """
                UPDATE Loan
                SET 
                book_id = ?,
                user_id = ?,
                loan_date = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, loan.getBook_id());
            stmt.setLong(2, loan.getUser_id());
            stmt.setDate(3, Date.valueOf(loan.getLoanDate()));
            stmt.setLong(4, loan.getId());

            int altersLines = stmt.executeUpdate();

            if (altersLines <= 0) {
                throw new RuntimeException("Failed on Update of the Loan");
            }
        }
    }

    @Override
    public void returnBook(Loan loan) throws SQLException {

        String command = """
                UPDATE Loan
                SET return_date = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setDate(1, Date.valueOf(loan.getReturnDate()));
            stmt.setLong(2, loan.getId());

            int altersLines = stmt.executeUpdate();

            if (altersLines <= 0) {
                throw new RuntimeException("Failed on Update of the Loan");
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {

        String command = """
                DELETE FROM Loan
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            int altersLines = stmt.executeUpdate();

            if (altersLines <= 0) {
                throw new RuntimeException("Failed on Delete of the Loan");
            }
        }
    }

}
