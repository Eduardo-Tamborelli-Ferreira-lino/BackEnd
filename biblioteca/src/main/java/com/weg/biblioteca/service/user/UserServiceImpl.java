package com.weg.biblioteca.service.user;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.biblioteca.model.User;
import com.weg.biblioteca.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) throws SQLException {

        if (user.getName() == null || user.getEmail() == null ||
        user.getName().isEmpty() || user.getEmail().isEmpty()) {
            throw new RuntimeException("The Name or Email can't be null");
        }

        userRepository.save(user);

        return user;

    }

    @Override
    public User findByID(Long id) throws SQLException {

        User user = userRepository.findByID(id).
            orElseThrow(() -> new RuntimeException("User not found"));

        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {

        List<User> users = userRepository.findAll();

        if (users == null  || users.isEmpty()) {
            throw new RuntimeException("Don't has any user on system");
        }

        return users;
    }

    @Override
    public User update(Long id, User user) throws SQLException {

        if (!userRepository.userExist(id)) {
            throw new RuntimeException("User not found");
        }

        user.setId(id);

        userRepository.update(user);

        return user;
    }

    @Override
    public void delete(Long id) throws SQLException {

        if (!userRepository.userExist(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.delete(id);
    }


}
