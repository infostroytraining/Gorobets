package com.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Converter;
import com.entity.LogEvent;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.service.LogEventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dto.LogEventDTO;
import com.service.exception.ServiceException;
import sun.rmi.runtime.Log;


/*Реализовать сервер логгирования, который принимает и сохраняет лог-сообщения в памяти или в базе данных, а также отдает имеющуюся лог-информацию по требованию.

По GET-запросу сервер обязан вернуть все сохраненные лог-сообщения, при этом желательно (но не обязательно):
1) реализовать возможность получения лог-сообщений в формате JSON, если в GET-запросе будет указан параметр format со значением pretty;
2) реализовать выделение получаемых лог-сообщений цветом в зависимости от уровня логгирования.
*/
public class LogsServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogEventService logEventService = (LogEventService) request.getServletContext().getAttribute("logEventService");
        String format = request.getParameter("format");
        List<String> logEventsString = null;
        String logEventsJson;

        if (Strings.isNullOrEmpty(format)&& format.equalsIgnoreCase("pretty")) {
            try {
                Map<Integer, LogEvent> logEventMap = logEventService.getAll();
                logEventsJson = new Gson().toJson(logEventMap);
            } catch (ServiceException e) {
                LOGGER.error("Exception in servlet LogsServlet", e);
                throw new ServletException(e);
            }
            response.getWriter().write(logEventsJson);

        } else {
            try {
                Map<Integer, LogEvent> logEventMap = logEventService.getAll();
                for (LogEvent logEvent : logEventMap.values()) {
                    String logValue = logEvent.getLogValue();
                    logEventsString = new ArrayList<>();

                    logEventsString.add(logValue);
                }
            } catch (ServiceException e) {
                LOGGER.error("Exception in servlet LogsServlet", e);
                throw new ServletException(e);
            }

            request.setAttribute("logEventsString", logEventsString);
        }


        request.getRequestDispatcher("home.jsp").forward(request, response);


    }
}


