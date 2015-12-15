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

    }


}
