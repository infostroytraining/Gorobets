package com.web.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.User;
import com.service.MemoryUserService;
import com.service.TransactionalUserService;
import com.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * UserServlet class-it's a servlet that forward users to registration form
 * It has:
 * doGet and doPost methods for handling requests and giving responses
 */
public class UserServlet extends HttpServlet {
    User user;
    private static final Logger LOGGER = LogManager.getLogger();

//    private UserService userService;

//    private MemoryUserService memoryUserService;
//
//    private TransactionalUserService transactionalUserService;
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
//        userService = (UserService) config.getServletContext().getAttribute("userService");
//        memoryUserService = (MemoryUserService) config.getServletContext().getAttribute("memoryUserService");
//        transactionalUserService = (TransactionalUserService) config.getServletContext().getAttribute("transactionalUserService");
    }

    /**
     * Get method that handle request parameter and  give response
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        LOGGER.entry(request, response);
//        try {
//            request.setAttribute("users", userService.getAll());
//
//
//        } catch (Exception ex) {
//            LOGGER.error("Exception in doGet method at UserServlet");
//            throw new ServletException(ex);
//        }
        request.getRequestDispatcher("homePage.jsp").forward(request, response);
    }


}
