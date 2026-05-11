package com.weg.school_management.repository.student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.school_management.model.Student;

public interface StudentRepository {

    Student createStudent (Student student) throws SQLException;

    Optional<Student> findStudentById (Long id) throws SQLException;

    List<Student> findAllStudents () throws SQLException;

    Student updateStudent (Student student) throws SQLException;

    void delete (Long id) throws SQLException;

}
