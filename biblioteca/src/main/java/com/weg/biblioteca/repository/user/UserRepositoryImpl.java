package com.weg.biblioteca.repository.user;

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
import com.weg.biblioteca.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Override
    public User save(User user) throws SQLException {

        String command = """
                INSERT INTO User(
                name,
                email
                )
                VALUES
                (?, ?)
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                Long generatedId = rs.getLong(1);

                user.setId(generatedId);

                return user;
            }
        }
        throw new RuntimeException("Failed on Save the User");
    }

    @Override
    public Optional<User> findByID(Long id) throws SQLException {

        String command = """
                SELECT 
                name,
                email
                FROM User
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                String name = rs.getString("name");
                String email = rs.getString("email");

                return Optional.of(new User(
                    id,
                    name,
                    email
                ));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws SQLException {

        List<User> users = new ArrayList<>();

        String command = """
                SELECT
                id,
                name,
                email
                FROM User
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                users.add(new User(
                    id,
                    name,
                    email
                ));
            }
            return users;
        }
    }

    @Override
    public Boolean userExist(Long id) throws SQLException {

        String command = """
                SELECT COUNT(0) as result
                FROM User
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
        throw new RuntimeException("ID not found");
    }

    @Override
    public void update(User user) throws SQLException {

        String command = """
                UPDATE User
                SET 
                name = ?,
                email = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setLong(3, user.getId());

            int alterLines = stmt.executeUpdate();

            if (alterLines <= 0) {
                throw new RuntimeException("Failed on Update the User");
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {

        String command = """
                DELETE FROM User
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            int alterLines = stmt.executeUpdate();

            if (alterLines <= 0) {
                throw new RuntimeException("Failed on Delete the User");
            }
        }
    }

}
