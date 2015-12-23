package com.web.servlet;


import com.dao.Converter;
import com.dto.LogEventDTO;
import com.entity.LogEvent;
import com.service.LogEventService;
import com.service.exception.ServiceException;
import org.apache.logging.log4j.util.Strings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertFalse;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LogsServletTest {

    private static final String FORMAT = "format";
    private static final String LOG_VALUE = "logValue";


    private LogsServlet servlet = new LogsServlet();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext context;
    @Mock
    private LogEventService service;
    @Mock
    private RequestDispatcher dispatcher;

    @Before
    public void init() {
        when(request.getServletContext()).thenReturn(context);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(context.getAttribute("logEventService")).thenReturn(service);

    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        servlet.doGet(request, response);
        verify(request).getRequestDispatcher("home.jsp");
    }

    @Test
    public void testDoPost() throws ServletException, IOException, ServiceException {
        mockGetRequestParams(FORMAT);
        servlet.doPost(request, response);

        verify(service).getAll();
        verify(Converter.toJSON(service.getAll()));
        assertFalse(Converter.toJSON(service.getAll()).isEmpty());

        verify(request).setAttribute(eq("logEventsJson"), anyList());

        verify(request).getRequestDispatcher("home.jsp");

    }

    @Test(expected = ServletException.class)
    public void testDoPostWithServiceException() throws ServletException, IOException, ServiceException {
        mockGetRequestParams(FORMAT);
        when(service.getAll()).thenThrow(ServiceException.class);
        servlet.doPost(request, response);
    }

    @Test
    public void testDoPostWithEmptyParamsFromRequest() throws ServletException, IOException, ServiceException {
        List<String> logEventsString = new ArrayList<>();

        mockGetRequestParams(Strings.EMPTY);

        ArgumentCaptor<LogEvent> argument = ArgumentCaptor.forClass(LogEvent.class);
        LogEvent logEvent = argument.getValue();

        String logValue = logEvent.getLogValue();

        verify(service).getAll();
        verify(service).getAll().values();
        verify(logEventsString.add(logValue));

        assertFalse(logEventsString.isEmpty());


        servlet.doPost(request, response);

        verify(request).setAttribute(eq("answer"), any(LogEvent.class));
        verify(request).setAttribute(eq("errors"), anyList());
    }


    private void mockGetRequestParams(String format) {
        when(request.getParameter("format")).thenReturn(format);

    }
}