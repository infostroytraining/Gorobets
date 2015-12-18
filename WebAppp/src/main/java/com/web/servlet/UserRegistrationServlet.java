package com.web.servlet;

import com.captcha.Captchas;
import com.customAppender.CustomAppender;
import com.customAppender.MyCustomAppender;
import com.customAppender.Test;
import com.dto.UserDTO;
import com.entity.User;
import com.service.MemoryUserService;
import com.service.TransactionalUserService;
import com.service.exception.ServiceException;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by invincible _g_d on 12/14/15.
 */
@WebServlet(name = "UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {
  org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("test");
    CustomAppender customAppender = new CustomAppender();



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        logger.addAppender(customAppender);
        logger.info("Enter to the doPost method at servlet");
        MemoryUserService memoryUserService = (MemoryUserService) request.getServletContext().getAttribute("memoryUserService");
        TransactionalUserService transactionalUserService = (TransactionalUserService)
                request.getServletContext().getAttribute("transactionalUserService");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String passwordc = request.getParameter("passwordc");

//		Image avatar = request.getParameter("avatar");// find out how to hand in a image????

        UserDTO userDTO = new UserDTO(email, password, name, surname);


//        errors
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
                logger.info("Exception in servlet");
                throw new ServletException(e);
            }

            try {
                request.setAttribute("statisticMap", memoryUserService.getEmailForEachUser());
            } catch (ServiceException e) {
                logger.info("Exception in servlet");
                throw new ServletException(e);
            }
            request.setAttribute("userDTO", userDTO);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);

            request.getRequestDispatcher("answerToUser.jsp").forward(request, response);
        }
        logger.info("Exit from the doPost method at servlet");
    }

    private List<String> validateForm(UserDTO userDTO, String passwordc) {
        logger.info("Enter to the validForm method at servlet");
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
        logger.info("Exit from the validForm method at servlet");
        return errors;

    }


}

