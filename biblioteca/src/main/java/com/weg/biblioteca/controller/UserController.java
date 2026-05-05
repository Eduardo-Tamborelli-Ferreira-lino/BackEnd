package com.weg.biblioteca.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.biblioteca.model.User;
import com.weg.biblioteca.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User save(@RequestBody User user) {

        try {

            userService.save(user);

            return user;

        }
        catch(SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {

        try {

            return userService.findByID(id);

        }
        catch(SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<User> findAll() {

        try {

            return userService.findAll();

        }
        catch(SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {

        try {

            return userService.update(id, user);

        }
        catch(SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping ("/{id}")
    public void Delete (@PathVariable Long id) {

        try {

            userService.delete(id);

        }
        catch(SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
