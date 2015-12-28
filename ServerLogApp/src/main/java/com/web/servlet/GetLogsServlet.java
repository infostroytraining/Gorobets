package com.web.servlet;

import com.entity.LogEvent;
import com.service.LogEventService;
import com.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GetLogsServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private LogEventService logEventService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logEventService = (LogEventService) config.getServletContext().getAttribute("logEventService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();


        String logValues = (String) context.getAttribute("logValue");
        String logValue =request.getParameter("logValue");
        System.out.println(logValues+"1");
        System.out.println(logValue+"2");
        LogEvent logEvent = new LogEvent(logValue);

        try {
            if (logEvent != null) {
                logEventService.add(logEvent);
            }
            logEventService.add(logEvent);
        } catch (ServiceException e) {
            LOGGER.error("Exception in servlet GetLogsServlet", e);
            throw new ServletException(e);
        }
        request.setAttribute("logEvent",logEvent);

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
