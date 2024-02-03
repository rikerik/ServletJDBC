package com.example.DAO;

import java.util.List;

import com.example.Model.User;

public interface UserDAO {
    void addUser(User user) throws ClassNotFoundException;

    User getUserById(Integer userId) throws ClassNotFoundException;

    List<User> getAllUsers() throws Exception;

    void update(Integer id, User updatedUser) throws ClassNotFoundException;

    void delete(Integer i) throws ClassNotFoundException;
}
