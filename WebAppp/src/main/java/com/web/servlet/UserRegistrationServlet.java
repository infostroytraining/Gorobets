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

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER_DTO = "userDTO";
    private static final String ERRORS = "errors";
    private static final String PASSWORD_C = "passwordc";

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Post method that handle request parameter and  give response
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOGGER.addAppender(customAppender);


        MemoryUserService memoryUserService = (MemoryUserService) request.getServletContext().getAttribute("memoryUserService");

        TransactionalUserService transactionalUserService = (TransactionalUserService)
                request.getServletContext().getAttribute("transactionalUserService");
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String passwordc = request.getParameter(PASSWORD_C);

        UserDTO userDTO = new UserDTO(email, password, name, surname);//for saving data at jsp form!

        List<String> errors = validateForm(userDTO, passwordc);
        if (!errors.isEmpty()) {
            request.setAttribute(USER_DTO, userDTO);
            request.setAttribute(ERRORS, errors);
            request.getRequestDispatcher("userErrors.jsp").forward(request, response);
        } else {
            User user = new User(email, password, name, surname);
            try {
                memoryUserService.add(user);
                request.setAttribute("statisticMap", memoryUserService.getEmailForEachUser());

            } catch (ServiceException e) {
                LOGGER.error("Exception in UserRegistrationServlet");
                throw new ServletException(e);
            }
            request.setAttribute(USER_DTO, userDTO);
            request.setAttribute(EMAIL, email);
            request.setAttribute(PASSWORD, password);
            request.setAttribute(NAME, name);
            request.setAttribute(SURNAME, surname);

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
    protected List<String> validateForm(UserDTO userDTO, String passwordc) {
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

