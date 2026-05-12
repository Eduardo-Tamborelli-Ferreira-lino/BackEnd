package com.weg.school_management.repository.classRepo;

import java.sql.SQLException;
import java.util.List;

import com.weg.school_management.model.Teacher;

public interface ClassRepository {

    List<Teacher> findAllTeachers(Long id) throws SQLException;
}
