package com.weg.school_management.repository.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.weg.school_management.connection.ConnectionFactory;
import com.weg.school_management.model.Teacher;

@Repository
public class TeacherRepositoryImpl implements TeacherRepository{

    @Override
    public Teacher createTeacher(Teacher teacher) throws SQLException {

        String command = """
                INSERT INTO teacher(
                name,
                email,
                subject
                )
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());
            stmt.setString(3, teacher.getSubject());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                
                Long generatedId = rs.getLong(1);

                teacher.setId(generatedId);

                return teacher;
            }
        }
        throw new RuntimeException("The teacher can't be create in the system. We have some error on the code.");
    }

    @Override
    public Optional<Teacher> findTeacherById(Long id) throws SQLException {

        String command = """
                SELECT
                name,
                email,
                subject
                FROM teacher
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String subject = rs.getString("subject");

                return Optional.of(new Teacher(
                    id,
                    name, 
                    email, 
                    subject
                ));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Teacher> findAllTeachers() throws SQLException {

        List<Teacher> teachers = new ArrayList<>();

        String command = """
                SELECT
                id,
                name,
                email,
                subject
                FROM teacher
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String subject = rs.getString("subject");

                teachers.add(new Teacher(
                    id, 
                    name, 
                    email, 
                    subject
                ));
            }
            return teachers;
        }
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) throws SQLException {

        String command = """
                UPDATE teacher
                set 
                name = ?,
                email = ?,
                subject = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());
            stmt.setString(3, teacher.getSubject());
            stmt.setLong(4, teacher.getId());

            int changeLines = stmt.executeUpdate();

            if (changeLines <= 0) {
                throw new RuntimeException("You can't edit the profile of an absent teacher");
            }

            return teacher;
        }
    }

    @Override
    public void deleteTeacher(Long id) throws SQLException {

        String command = """
                DELETE FROM teacher
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            int changeLines = stmt.executeUpdate();

            if (changeLines <= 0) {
                throw new RuntimeException("You can't delete this teacher");
            }
        }
    }

}
