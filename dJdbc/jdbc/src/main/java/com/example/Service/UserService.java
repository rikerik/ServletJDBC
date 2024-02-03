package com.example.Service;

import java.util.List;
import java.util.Optional;

import com.example.Model.User;

public interface UserService {
    Optional<User> getUserById(Integer userId) throws ClassNotFoundException;

    List<User> getAllUsers() throws ClassNotFoundException;

    void addUser(User user) throws ClassNotFoundException;

    void updateUser(Integer userId, User user) throws ClassNotFoundException;

    void deleteUser(Integer userId) throws ClassNotFoundException;

}
