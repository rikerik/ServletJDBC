package com.example.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.DAO.GenericDAO;
import com.example.DB.DbConnector;
import com.example.Model.User;

public class UserDaoImpl implements GenericDAO<User, Integer> {

    @Override
    public void save(User user) throws ClassNotFoundException {
        String sql = "INSERT INTO users (user_name, password) VALUES (?, ?)";

        try (Connection con = DbConnector.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> get(Integer userId) throws ClassNotFoundException {
        Optional<User> user = null;

        String sql = "SELECT user_id, user_name, password FROM users WHERE user_id = ?";
        try (Connection conn = DbConnector.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int Integer = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String password = rs.getString("password");
                user = Optional.of(new User(Integer, name, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() throws ClassNotFoundException {
        List<User> userList = new ArrayList<User>();
        String sql = "SELECT * FROM users";

        try (Connection con = DbConnector.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void update(Integer id, User updatedUser) throws ClassNotFoundException {
        String sql = "UPDATE users SET user_name=?, password=? WHERE user_id = ?";

        try (Connection conn = DbConnector.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedUser.getUserName());
            preparedStatement.setString(2, updatedUser.getPassword());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws ClassNotFoundException {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DbConnector.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
