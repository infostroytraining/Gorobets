package com.web.servlet;

import com.customAppender.CustomAppender;
import com.dto.UserDTO;
import com.entity.User;
import com.service.MemoryUserService;
import com.service.TransactionalUserService;
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
 * UserRegistrationServlet class-it's a servlet that forward users to registration form
 * It has: doGet and doPost methods for handling requests and giving responses
 */
@WebServlet(name = "UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {

    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger("CustomAppender");

    CustomAppender customAppender = new CustomAppender();


    /**
     * Get method that handle request parameter and  give response
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Post method that handle request parameter and  give response
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOGGER.addAppender(customAppender);


        MemoryUserService memoryUserService = (MemoryUserService) request.getServletContext().getAttribute("memoryUserService");

        TransactionalUserService transactionalUserService = (TransactionalUserService)
                request.getServletContext().getAttribute("transactionalUserService");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String passwordc = request.getParameter("passwordc");

        UserDTO userDTO = new UserDTO(email, password, name, surname);//for saving data at jsp form!

        List<String> errors = validateForm(userDTO, passwordc);
        if (!errors.isEmpty()) {
            request.setAttribute("userDTO", userDTO);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("userErrors.jsp").forward(request, response);
        } else {
            User user = new User(email, password, name, surname);
            try {
                memoryUserService.add(user);
            } catch (ServiceException e) {
                LOGGER.error("Exception in UserRegistrationServlet");
                throw new ServletException(e);
            }

            try {
                request.setAttribute("statisticMap", memoryUserService.getEmailForEachUser());
            } catch (ServiceException e) {
                LOGGER.error("Exception in servlet");
                throw new ServletException(e);
            }
            request.setAttribute("userDTO", userDTO);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);

            request.getRequestDispatcher("answerToUser.jsp").forward(request, response);
        }

    }

    /**
     * ValidateForm method check the data for errors like NullPointerExceptions
     *
     * @param userDTO
     * @param passwordc
     * @return
     */
    private List<String> validateForm(UserDTO userDTO, String passwordc) {
        LOGGER.info("Enter to the validForm method at UserRegistrationServlet");
        List<String> errors = new ArrayList<>();


        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            errors.add("Please, input your email");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            errors.add("Please, input your password");
        }
        if (userDTO.getName() == null || userDTO.getName().isEmpty()) {
            errors.add("Please, input your name");
        }

        if (userDTO.getSurname() == null || userDTO.getSurname().isEmpty()) {
            errors.add("Please, input your surname");
        }
        if (passwordc == null || passwordc.isEmpty()) {
            errors.add("Please, input right code and push submit");
        }
        LOGGER.info("Exit from the validForm method at UserRegistrationServlet");
        return errors;

    }


}

