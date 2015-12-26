package com.web.servlet;

import com.captcha.Captchas;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CaptchaServlet class-it's a servlet that forward users to logIn form in doGet method and verify if captcha value
 * from user response is right.
 * It has: doGet and doPost methods for handling requests from users and giving responses
 */
@WebServlet(name = "СaptchaServlet")
public class CaptchaServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Post method that handle request parameter and  give response
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Get method that handle request parameter, check captcha for right value  and give response
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Construct the captchas object
// Use same settings as in query.jsp
        LOGGER.entry("Entry to check captcha method");
        Captchas captchas = new Captchas(
                request.getSession(true),  // Ensure session
                "demo", // client
                "secret" // secret
        );
// Read the form values
        String message = request.getParameter("message");
        String password = request.getParameter("password");

// Check captcha
        String body;
        switch (captchas.check(password)) {
            case 's':
                body = "Session seems to be timed out or broken. ";
                body += "Please try again or report error to administrator.";
                break;
            case 'm':
                body = "Every CAPTCHA can only be used once. ";
                body += "The current CAPTCHA has already been used. ";
                body += "Please use back button and reload";
                break;
            case 'w':
                body = "You entered the wrong password. ";
                body += "Please use back button and try again.  ";
                break;
            default:
                body = "Your message was verified to be entered by a human and is \"" + message + "\"";
                break;

        }
        LOGGER.exit("Exit from check captcha method");
        request.setAttribute("body", body);
        request.getRequestDispatcher("homePage.jsp").forward(request, response);
    }
}
