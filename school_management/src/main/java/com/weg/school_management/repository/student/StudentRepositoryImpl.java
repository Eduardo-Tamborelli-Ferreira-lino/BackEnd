package com.weg.school_management.repository.student;

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

import com.weg.school_management.connection.ConnectionFactory;
import com.weg.school_management.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

    @Override
    public Student createStudent(Student student) throws SQLException {

        String command = """
                INSERT INTO student(
                name,
                email,
                registration,
                date_birth)
                VALUES
                (?, ?, ?, ?)
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getRegistration());
            stmt.setDate(4, Date.valueOf(student.getDateBirth()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                Long generatedId = rs.getLong(1);

                student.setId(generatedId);

                return student;
            }
        }
        throw new RuntimeException("Can't save the student in the system.");
    }

    @Override
    public Optional<Student> findStudentById(Long id) throws SQLException {

        String command = """
                SELECT
                name,
                email,
                registration,
                date_birth
                FROM student
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                String name = rs.getString("name");
                String email = rs.getString("email");
                String registration = rs.getString("registration");
                LocalDate dateBirth = rs.getDate("date_birth").toLocalDate();

                return Optional.of(new Student(
                    id,
                    name,
                    email,
                    registration,
                    dateBirth
                ));
            }
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findAllStudents() throws SQLException {

        List<Student> students = new ArrayList<>();

        String command = """
                SELECT
                id,
                name,
                email,
                registration,
                date_birth
                FROM student
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String registration = rs.getString("registration");
                LocalDate dataBirth = rs.getDate("date_birth").toLocalDate();

                students.add(new Student(
                    id,
                    name, 
                    email, 
                    registration, 
                    dataBirth
                ));
            }
            return students;
        }
    }

    @Override
    public Student updateStudent(Student student) throws SQLException {

        String command = """
                UPDATE student
                SET 
                name = ?,
                email = ?,
                registration = ?,
                date_birth = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {
            
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getRegistration());
            stmt.setDate(4, Date.valueOf(student.getDateBirth()));
            stmt.setLong(5, student.getId());

            int changeLines = stmt.executeUpdate();

            if (changeLines <= 0) {
                throw new RuntimeException("You can't edit the profile of an absent student");
            }

            return student;
        }
    }

    @Override
    public void delete(Long id) throws SQLException {

        String command = """
                DELETE FROM student
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);

            int changeLines = stmt.executeUpdate();

            if (changeLines <= 0) {
                throw new RuntimeException("You can't delete this student");
            }
        }
    }

}
