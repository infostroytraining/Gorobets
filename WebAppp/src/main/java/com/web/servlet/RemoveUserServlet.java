package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.UserService;
import com.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Integers;


public class RemoveUserServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();


    private UserService userService;
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");


    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        int id = Integers.parseInt(userId);
        LOGGER.debug("User id to remove {}", id);

        try {
            userService.remove(id);
        } catch (ServiceException e) {
            LOGGER.debug("Exception in RemoveUserServlet while removing user with id {}", id);
            throw new ServletException();


        }
    }
}
