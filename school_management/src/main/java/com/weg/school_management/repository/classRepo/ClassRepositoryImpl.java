package com.weg.school_management.repository.classRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.school_management.connection.ConnectionFactory;
import com.weg.school_management.model.Teacher;

@Repository
public class ClassRepositoryImpl implements ClassRepository{

    @Override
    public List<Teacher> findAllTeachers(Long idCourse) throws SQLException {

        List<Teacher> teacherIds = new ArrayList<>();

        String command = """
                SELECT
                t.id,
                t.name
                FROM class cl
                JOIN teacher t
                ON t.id = cl.teacher_id
                WHERE course_id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, idCourse);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                teacherIds.add(new Teacher(
                    rs.getLong("t.id"),
                    rs.getString("t.name")
                ));
            }
            return teacherIds;
        }
    }

}
