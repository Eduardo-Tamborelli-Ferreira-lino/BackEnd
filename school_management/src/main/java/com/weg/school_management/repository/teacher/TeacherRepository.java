package com.weg.school_management.repository.teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.school_management.model.Teacher;

public interface TeacherRepository {

    Teacher createTeacher(Teacher teacher) throws SQLException;

    Optional<Teacher> findTeacherById(Long id) throws SQLException;

    List<Teacher> findAllTeachers() throws SQLException;

    Teacher updateTeacher(Teacher teacher) throws SQLException;

    void deleteTeacher (Long id) throws SQLException;
}
