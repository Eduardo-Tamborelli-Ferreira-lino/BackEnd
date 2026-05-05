package com.weg.biblioteca.repository.user;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.biblioteca.model.User;

public interface UserRepository {

    User save(User user) throws SQLException;

    Optional<User> findByID(Long id) throws SQLException;

    List<User> findAll() throws SQLException;

    Boolean userExist(Long id) throws SQLException;

    void update (User user) throws SQLException;

    void delete (Long id) throws SQLException;
}
