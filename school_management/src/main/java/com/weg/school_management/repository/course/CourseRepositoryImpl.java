package com.weg.school_management.repository.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.weg.school_management.connection.ConnectionFactory;
import com.weg.school_management.model.Course;

@Repository
public class CourseRepositoryImpl implements CourseRepository{

    @Override
    public Course createCourse(Course course) throws SQLException {

        String command = """
                INSERT INTO course (
                name,
                code
                )
                VALUES
                (?, ?)
                """;
        
        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, course.getName());
            stmt.setString(2, course.getCode());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                
                Long generatedId = rs.getLong(1);

                course.setId(generatedId);

                return course;
            }
        }
        throw new RuntimeException("The course can't be create in the system. We have some error on the code.");
    }

    @Override
    public Optional<Course> findCourseById(Long id) throws SQLException {

        String command = """
                SELECT
                co.name,
                co.code
                FROM course co
                WHERE id = ?
                """;
        
        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)){

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Course(
                    id,
                    rs.getString("name"), 
                    rs.getString("code")
                ));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Course> findAllCourses() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllCourses'");
    }

    @Override
    public Course updateCourse(Course course) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCourse'");
    }

    @Override
    public void deleteCourse(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCourse'");
    }

}
