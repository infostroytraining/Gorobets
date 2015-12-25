package com.web.servlet;

import com.dao.exception.DAOException;
import com.dao.postgesql.PostgresUserDAO;
import com.entity.User;
import com.service.MemoryUserService;
import com.service.TransactionalUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserLogInServlet class-it's a servlet that forward users to logIn form in doGet method and to Hello page in doPost method
 * It has: doGet and doPost methods for handling requests from users and giving responses
 */
@WebServlet(name = "UserLogInServlet")
public class UserLogInServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    PostgresUserDAO postgresUserDAO;
    private MemoryUserService memoryUserService;

    private TransactionalUserService transactionalUserService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        PostgresUserDAO postgresUserDAO = (PostgresUserDAO) config.getServletContext().getAttribute("postgresUserDAO");

        memoryUserService = (MemoryUserService) config.getServletContext().getAttribute("memoryUserService");

        transactionalUserService = (TransactionalUserService) config.getServletContext().getAttribute("transactionalUserService");
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
        User user ;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            user = postgresUserDAO.getUserByUserEmail(email);
            String name = user.getName();
            request.setAttribute("name", name);
        } catch (DAOException e) {
            LOGGER.error("Exception in doPost method at  UserLogInServlet");
            throw new ServletException(e);
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

    /**
     * * ValidateForm method check the data for errors like NullPointerExceptions
     *
     * @param user
     * @param email
     * @param password
     * @return
     */
    private List<String> validateForm(User user, String email, String password) {
        List<String> errors = new ArrayList<>();


        if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().equals(email)) {
            errors.add("Please, input right email");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() || !user.getPassword().equals(password)) {
            errors.add("Please, input right password");
        }

        return errors;
    }


}

