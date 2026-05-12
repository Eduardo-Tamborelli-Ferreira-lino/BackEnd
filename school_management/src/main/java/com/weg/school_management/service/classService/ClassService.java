package com.weg.school_management.service.classService;

import java.sql.SQLException;

public interface ClassService {

    void findAllTeachers(Long id) throws SQLException;
}
