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
//       String email ="email";
//        String password = "password";
//        String name = "name";
//        String surname = "surname";
//
//        try {
//            // Set the attribute and Forward to hello.jsp
//            request.setAttribute("email", email);
//            request.setAttribute("password", password);
//            request.setAttri bute("name", name);
//            request.setAttribute("surname", surname);
            request.getRequestDispatcher(
                    "user.jsp").forward(request, response);
//        } catch (Exception ex) {
//            ex.printStackTrace ();
//        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UserStorage userStorage = new UserStorage();
//        UserDAO userDAO = new MemoryUserDAO(userStorage);
//        MemoryUserService memoryUserService = new MemoryUserService(userDAO);
                // MemoryUserService memoryUserService = (MemoryUserService) request.getServletContext().getAttribute("memoryUserService");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
//		Image avatar = request.getParameter("avatar");// find out how to hand in a image????
        UserDTO user = new UserDTO(email, password, name, surname);


        //errors
//        List<String> errors = validateForm(userDTO);
//        if (!errors.isEmpty()) {
//            request.setAttribute("userDTO", userDTO);
//            request.setAttribute("errors", errors);
//            request.getRequestDispatcher("userErrors.jsp").forward(request, response);
//        } else {
//            User user =new User(email, password, name, surname);
//            memoryUserService.add(user);
//            request.setAttribute("statisticMap", memoryUserService.getEmailForEachUser());
            request.setAttribute("user",user);
//            request.setAttribute("email", email);
//            request.setAttribute("password", password);
//            request.setAttribute("name", name);
//            request.setAttribute("surname", surname);

            request.getRequestDispatcher("userErrors.jsp").forward(request, response);
//        }
    }

//    private List<String> validateForm(UserDTO user) {
//        List<String> errors = new ArrayList<>();
//
//        if (user.getEmail() == null || user.getEmail().isEmpty()) {
//            errors.add("Please, input your email");
//        }
//        if (user.getPassword() == null || user.getPassword().isEmpty()) {
//            errors.add("Please, input your password");
//        }
//        if (user.getName() == null || user.getName().isEmpty()) {
//            errors.add("Please, input your name");
//        }
//
//        if (user.getSurname() == null || user.getSurname().isEmpty()) {
//            errors.add("Please, input your surname");
//        }
//        return errors;
//    }


}