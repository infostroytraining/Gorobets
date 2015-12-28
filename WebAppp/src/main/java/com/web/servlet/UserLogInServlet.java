package com.web.servlet;


import com.entity.User;
import com.google.common.base.Strings;
import com.google.gson.Gson;

import com.service.UserService;
import com.service.exception.ServiceException;
import com.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.HashMap;

import java.util.Map;

/**
 * UserLogInServlet class-it's a servlet that forward users to logIn form in doGet method and to Hello page in doPost method
 * It has: doGet and doPost methods for handling requests from users and giving responses
 */
@WebServlet(name = "UserLogInServlet")
public class UserLogInServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private UserService userService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        userService = (UserService) config.getServletContext().getAttribute("userService");

    }

    /**
     * Get method that handle request parameter and  give response
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            request.getRequestDispatcher("userLogInPage.jsp").forward(request, response);

        } catch (Exception ex) {
            LOGGER.error("Exception in doGet method at  UserLogInServlet");
            throw new ServletException(ex);
        }
    }

    /**
     * * Post method that handle request parameter and  give response
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        PostgresUserDAO postgresUserDAO = (PostgresUserDAO) request.getSession().getServletContext().getAttribute("postgresUserDAO");
        User user;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            user = userService.getUserByUserEmail(email);
            Map<String, String> errors = validateForm(user, email, password);
            if (!errors.isEmpty()) {
                response.setStatus(400);
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(new Gson().toJson(errors));
                LOGGER.info("Errors while add a user");

            } else {
                response.setStatus(200);
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(new Gson().toJson(user));
                LOGGER.info("user was created!");
                request.getRequestDispatcher("Hello.jsp").forward(request, response);


            }

        } catch (ServiceException e) {
            LOGGER.error("Exception in doPost method at  UserLogInServlet");
            throw new ServletException(e);
        }


    }


    /**
     * * ValidateForm method check the data for errors like NullPointerExceptions
     *
     * @param user
     * @param email
     * @param password
     * @return
     */
    private Map<String, String> validateForm(User user, String email, String password) {

        Map<String, String> errors = new HashMap<>();
        if (user != null) {
            if (!Validator.validateEmailAddress(user.getEmail())) {
                errors.put(EMAIL, "Please, input a correct email");
            }
            if (!Validator.validatePassword(user.getPassword())) {
                errors.put(PASSWORD, "Password must contain at least one lowercase letter, " +
                        "one uppercase letter, one number and have a length of 8 characters");
            }
            if (Strings.isNullOrEmpty(user.getEmail()) || !user.getEmail().equals(email)) {
                errors.put(EMAIL, "Please, enter the  email");
            }
            if (Strings.isNullOrEmpty(user.getPassword()) || !user.getPassword().equals(password)) {
                errors.put(PASSWORD, "Please, enter the password");
            }
        }

        return errors;
    }


}

