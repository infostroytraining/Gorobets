package com.web.servlet;

import com.entity.LogEvent;
import com.service.LogEventService;
import com.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by invincible_g_d on 12/18/15.
 */
public class GetLogsServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logValue = request.getParameter("logEvent");
        LogEvent logEvent = new LogEvent(logValue);
        LogEventService logEventService = (LogEventService) request.getServletContext().getAttribute("logEventService");
        try {
            logEventService.add(logEvent);
        } catch (ServiceException e) {
            LOGGER.error("Exception in servlet", e);
            throw new ServletException(e);
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
