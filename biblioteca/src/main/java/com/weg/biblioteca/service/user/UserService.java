package com.weg.biblioteca.service.user;

import java.sql.SQLException;
import java.util.List;

import com.weg.biblioteca.model.User;

public interface UserService {

    User save(User user) throws SQLException;

    User findByID(Long id) throws SQLException;

    List<User> findAll() throws SQLException;

    User update (Long id, User user) throws SQLException;

    void delete (Long id) throws SQLException;
}
