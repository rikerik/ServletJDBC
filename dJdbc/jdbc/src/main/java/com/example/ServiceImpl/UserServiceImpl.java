package com.example.ServiceImpl;

import java.util.List;
import java.util.Optional;

import com.example.DAO.GenericDAO;
import com.example.Model.User;
import com.example.Service.UserService;

public class UserServiceImpl implements UserService {

    private GenericDAO<User, Integer> userDao;

    public UserServiceImpl(GenericDAO<User, Integer> userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> getUserById(Integer userId) throws ClassNotFoundException {
        return userDao.get(userId);
    }

    @Override
    public List<User> getAllUsers() throws ClassNotFoundException {
        return (List<User>) userDao.getAll();
    }

    @Override
    public void addUser(User user) throws ClassNotFoundException {
        userDao.save(user);
    }

    @Override
    public void updateUser(Integer userId, User user) throws ClassNotFoundException {
        userDao.update(userId, user);
    }

    @Override
    public void deleteUser(Integer userId) throws ClassNotFoundException {
        userDao.delete(userId);
    }

}
