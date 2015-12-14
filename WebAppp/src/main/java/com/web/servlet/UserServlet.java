package com.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.dao.memory.MemoryUserDAO;
import com.dao.storage.UserStorage;
import com.entity.User;
import com.dto.UserDTO;
import com.service.MemoryUserService;

public class UserServlet extends HttpServlet {
    User user;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            request.getRequestDispatcher("userErrors.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace ();
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UserStorage userStorage = new UserStorage();
//        UserDAO userDAO = new MemoryUserDAO(userStorage);
//        MemoryUserService memoryUserService = new MemoryUserService(userDAO);
//        MemoryUserService memoryUserService = (MemoryUserService) request.getServletContext().getAttribute("memoryUserService");
//
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
////		Image avatar = request.getParameter("avatar");// find out how to hand in a image????
//        UserDTO userDTO = new UserDTO(email, password, name, surname);
//
//
////        errors
//        List<String> errors = validateForm(userDTO);
//        if (!errors.isEmpty()) {
//            request.setAttribute("userDTO", userDTO);
//            request.setAttribute("errors", errors);
//            request.getRequestDispatcher("userErrors.jsp").forward(request, response);
//        } else {
//            User user =new User(email, password, name, surname);
//            memoryUserService.add(user);
//            request.setAttribute("statisticMap", memoryUserService.getEmailForEachUser());
//            request.setAttribute("userDTO",userDTO);
//            request.setAttribute("email", email);
//            request.setAttribute("password", password);
//            request.setAttribute("name", name);
//            request.setAttribute("surname", surname);
//
//            request.getRequestDispatcher("answerToUser.jsp").forward(request, response);
//        }
//    }
//
//    private List<String> validateForm(UserDTO userDTO) {
//        List<String> errors = new ArrayList<>();
//
//        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
//            errors.add("Please, input your email");
//        }
//        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
//            errors.add("Please, input your password");
//        }
//        if (userDTO.getName() == null || userDTO.getName().isEmpty()) {
//            errors.add("Please, input your name");
//        }
//
//        if (userDTO.getSurname() == null || userDTO.getSurname().isEmpty()) {
//            errors.add("Please, input your surname");
//        }
//        return errors;
    }


}
