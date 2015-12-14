package com.web.servlet;

import com.captcha.Captchas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by invincible_g_d on 12/14/15.
 */
@WebServlet(name = "СaptchaServlet")
public class CaptchaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Construct the captchas object
// Use same settings as in query.jsp
        Captchas captchas = new Captchas(////was like that ...= new captchas.Captchas(....
                request.getSession(true),     // Ensure session
                request.getParameter("surname"),                      // client
                "secret"                    // secret
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
        request.setAttribute("body",body);
        request.getRequestDispatcher("userLogin.jsp").forward(request,response);
    }
}
