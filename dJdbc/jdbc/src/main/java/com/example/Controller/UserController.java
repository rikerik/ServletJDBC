package com.example.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.DAO.GenericDAO;
import com.example.DAOImpl.UserDaoImpl;
import com.example.DTO.UserCreateDTO;
import com.example.DTO.UserDTO;
import com.example.Mapper.UserCreateMapper;
import com.example.Mapper.UserMapper;
import com.example.Model.User;
import com.example.ServiceImpl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet", urlPatterns = { "/users", "/users/create", "/users/update", "/users/delete" })
public class UserController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    GenericDAO<User, Integer> userDao = new UserDaoImpl(); // You should replace UserDaoImpl with your actual
                                                           // implementation
    UserServiceImpl userService = new UserServiceImpl(userDao);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userIdParam = request.getParameter("userId");

            if (userIdParam != null && !userIdParam.isEmpty()) {
                int userId = Integer.parseInt(userIdParam);
                Optional<User> optionalUser = userService.getUserById(userId);

                if (optionalUser.isPresent()) {
                    UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(optionalUser.get());
                    response.getWriter().write("UserDTO: " + userDTO.toString());
                    logger.info("User listed by id: " + userId);
                } else {
                    response.getWriter().write("User not found");
                    logger.info("User not found for id: " + userId);
                }
            } else {
                List<User> users = userService.getAllUsers();
                response.getWriter().write("All users: " + users.toString());
                logger.info("All users are listed");
            }
        } catch (NumberFormatException | ClassNotFoundException | IOException e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
            logger.error("Exception occurred: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("userName");
        String password = request.getParameter("password");

        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUserName(username);
        userCreateDTO.setPassword(password);

        User newUser = UserCreateMapper.INSTANCE.userCreateDTOToUser(userCreateDTO);

        try {
            userService.addUser(newUser);
            response.getWriter().write("User created successfully");
            logger.info("User created successfully");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            logger.error("Exception occurred: " + e.getMessage());
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userIdParam = request.getParameter("userId");
            String updatedName = request.getParameter("updatedName");
            String updatedPassword = request.getParameter("updatedPassword");

            if (userIdParam != null && !userIdParam.isEmpty()) {
                int userId = Integer.parseInt(userIdParam);
                Optional<User> optionalUser = userService.getUserById(userId);

                if (optionalUser.isPresent()) {
                    User userToUpdate = optionalUser.get();
                    userToUpdate.setUserName(updatedName);
                    userToUpdate.setPassword(updatedPassword);

                    userService.updateUser(userId, userToUpdate);

                    response.getWriter().write("User updated successfully");
                    logger.info("User updated successfully: " + userToUpdate.toString());
                } else {
                    response.getWriter().write("User not found for id: " + userId);
                    logger.info("User not found for id: " + userId);
                }
            } else {
                response.getWriter().write("Invalid user ID");
                logger.info("Invalid user ID");
            }
        } catch (NumberFormatException | ClassNotFoundException | IOException e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
            logger.error("Exception occurred: " + e.getMessage());
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userIdParam = request.getParameter("userId");

            if (userIdParam != null && !userIdParam.isEmpty()) {
                int userId = Integer.parseInt(userIdParam);
                userService.deleteUser(userId);

                response.getWriter().write("User deleted successfully");
                logger.info("User deleted successfully with ID: " + userId);
            } else {
                response.getWriter().write("Invalid user ID");
                logger.info("Invalid user ID");
            }
        } catch (NumberFormatException | ClassNotFoundException | IOException e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
            logger.error("Exception occurred: " + e.getMessage());
        }
    }

}