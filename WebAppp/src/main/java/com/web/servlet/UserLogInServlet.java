package com.web.servlet;

import com.dao.exception.DAOException;
import com.dao.postgesql.PostgresUserDAO;
import com.dto.UserDTO;
import com.entity.User;
import com.service.MemoryUserService;
import com.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by invincible _g_d on 12/14/15.
 */
@WebServlet(name = "UserRegistrationServlet")
public class UserLogInServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            request.getRequestDispatcher("userLogInPage.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PostgresUserDAO postgresUserDAO = (PostgresUserDAO) request.getServletContext().getAttribute("postgresUserDAO");
        User user = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            user = postgresUserDAO.getUserByUserEmail(email);

        } catch (DAOException e) {
            e.printStackTrace();
        }
        if ((user != null) && user.getPassword().equals(password)) {
            request.getRequestDispatcher("Hello.jsp").forward(request, response);
        } else {

            List<String> errors = validateForm(user, email, password);
            if (!errors.isEmpty()) {
                request.setAttribute("user", user);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("errorLogInPage.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("Hello.jsp").forward(request, response);


            }
        }
    }

    private List<String> validateForm(User user,String email, String password) {
        List<String> errors = new ArrayList<>();


        if (user.getEmail() == null || user.getEmail().isEmpty()|| !user.getEmail().equals(email)) {
            errors.add("Please, input right email");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()|| !user.getPassword().equals(password)) {
            errors.add("Please, input right password");
        }

        return errors;
    }


}

