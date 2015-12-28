package com.web.servlet;

import com.customAppender.CustomAppender;
import com.dto.UserDTO;
import com.entity.User;
import com.google.common.base.Strings;
import com.google.gson.Gson;

import com.service.MemoryUserService;
import com.service.TransactionalUserService;
import com.service.UserService;
import com.service.exception.ServiceException;
import com.validator.Validator;
import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/*1. Сделать пользовательский интерфейс приложения регистрации пользователя с использованием Bootstrap.
2. Сделать клиентскую валидацию:
     - все обязательные поля не должны быть пустыми.
     - пароль должен содержать 8 символов, из них хотя бы одна маленькая буква, хотя бы одна большая буква, хотя бы одна              цифра.
     - электронный адресс должен быть валидным.
3. Сделать серверную валидацию без обновления страницы (с использованием Ajax). Проверить, существует ли пользователь с       введенным логином (электронным адрессом), и если да, то отобразить сообщение, что такой пользователь уже существует в       системе.

*/

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
    private static final String CAPTCHA = "captcha";
    private static final String CODE = "code";

    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UserRegistrationServlet.class);

//    CustomAppender customAppender = new CustomAppender();

    private UserService userService;
private TransactionalUserService transactionalUserService;
private MemoryUserService memoryUserService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);


        userService = (UserService) config.getServletContext().getAttribute("userService");
//        memoryUserService = (MemoryUserService) config.getServletContext().getAttribute("memoryUserService");
//        transactionalUserService = (TransactionalUserService) config.getServletContext().getAttribute("transactionalUserService");

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

//        LOGGER.addAppender(customAppender);


        String captcha = (String) request.getSession().getAttribute(CAPTCHA);
        String code = request.getParameter(CODE);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);


        UserDTO userDTO = new UserDTO(email, password, name, surname);//for saving data at jsp form!

        Map<String, String> errors = validateForm(userDTO, captcha, code);
        if (!errors.isEmpty()) {

            response.setStatus(400);
            response.setHeader("Content-Type", "application/json");
            response.getWriter().write(new Gson().toJson(errors));
            LOGGER.info("Errors while add a user");

        } else {

            User user = new User(email, password, name, surname);
            response.setStatus(200);
            response.setHeader("Content-Type", "application/json");
            response.getWriter().write(new Gson().toJson(user));
            LOGGER.info("user was created!");
            try {
//                transactionalUserService.add(user);
//                memoryUserService.add(user);
                userService.add(user);
//                request.setAttribute("statisticMap", userService.getEmailForEachUser());

            } catch (ServiceException e) {
                LOGGER.error("Exception in UserRegistrationServlet");
                throw new ServletException(e);
            }

        }

        //        customAppender.sendPost(customAppender);
    }


    /**
     * ValidateForm method check the data for errors like NullPointerExceptions
     *
     * @param userDTO
     * @param captcha
     * @param code
     * @return
     */
    protected Map<String, String> validateForm(UserDTO userDTO, String captcha, String code) {
        LOGGER.info("Enter to the validForm method at UserRegistrationServlet");

        Map<String, String> errors = new HashMap<>();

        if (!Validator.validateEmailAddress(userDTO.getEmail())) {
            errors.put(EMAIL, "Please, input a correct email");
        }
        if (!Validator.validatePassword(userDTO.getPassword())) {
            errors.put(PASSWORD, "Password must contain at least one lowercase letter, " +
                    "one uppercase letter, one number and have a length of 8 characters");
        }

        if (Strings.isNullOrEmpty(userDTO.getEmail())) {
            errors.put(EMAIL, "Please, input your email");
        }
        if (Strings.isNullOrEmpty(userDTO.getPassword())) {
            errors.put(PASSWORD, "Please, input your password");
        }
        if (Strings.isNullOrEmpty(userDTO.getName())) {
            errors.put(NAME, "Please, input your name");
        }
        if (Strings.isNullOrEmpty(userDTO.getSurname())) {
            errors.put(SURNAME, "Please, input your surname");
        }
        if (!captcha.equalsIgnoreCase(code)) {

            errors.put(CODE, "Please, input right code and push submit");
        }

        LOGGER.info("Exit from the validForm method at UserRegistrationServlet");
        return errors;

    }


}

