//package com.web.servlet;
//
//import com.dto.LogEventDTO;
//import com.entity.LogEvent;
//import com.service.LogEventService;
//import com.service.exception.ServiceException;
//
//
//import org.apache.logging.log4j.util.Strings;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//import static junit.framework.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
///**
// *
// */
//public class GetLogsServletTest  {
//
//
//    private static final String LOG_VALUE = "logValue";
//
//
////    private LogsServlet servlet = new LogsServlet();
//
//    @Mock
//    private HttpServletRequest request;
//
//    @Mock
//    private LogsServlet servlet;
//    @Mock
//    private HttpServletResponse response;
//    @Mock
//    private ServletContext context;
//    @Mock
//    private LogEventService service;
//    @Mock
//    private RequestDispatcher dispatcher;
//
//    @Before
//    public void init(){
//        when(request.getServletContext()).thenReturn(context);
//        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
//        when(context.getAttribute("logEventService")).thenReturn(service);
//
//    }
//
//    @Test
//    public void testDoPost() throws ServletException, IOException {
//        servlet.doGet(request, response);
//
//    }
//
//    @Test
//    public void testDoGet() throws ServletException, IOException, ServiceException {
//        mockGetRequestParams(LOG_VALUE);
//        servlet.doPost(request, response);
//        ArgumentCaptor<LogEvent> argument = ArgumentCaptor.forClass(LogEvent.class);
//        LogEvent logEvent = argument.getValue();
//
//
//        assertEquals(LOG_VALUE, logEvent.getLogValue());
//        verify(request).getRequestDispatcher("home.jsp");
//
//    }
//
//    @Test(expected=ServletException.class)
//    public void testDoGetWithServiceException() throws ServletException, IOException, ServiceException{
//        mockGetRequestParams(LOG_VALUE);
//        when(service.add(any(LogEvent.class))).thenThrow(ServiceException.class);
//        servlet.doGet(request, response);
//    }
//
//    @Test
//    public void testDoGetWithEmptyParamsFromRequest() throws ServletException, IOException{
//        mockGetRequestParams(Strings.EMPTY);
//        servlet.doGet(request, response);
//        ArgumentCaptor<LogEvent> argument = ArgumentCaptor.forClass(LogEvent.class);
//        LogEvent logEvent = argument.getValue();
//        assertEquals(null, logEvent.getLogValue());
//    }
//
//
//
//    private void mockGetRequestParams(String logValue){
//        when(request.getParameter("logValue")).thenReturn(logValue);
//
//    }
//}