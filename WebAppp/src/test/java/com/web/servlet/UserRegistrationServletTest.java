package com.web.servlet;


import com.customAppender.CustomAppender;
import com.dto.UserDTO;
import com.entity.User;
import com.service.MemoryUserService;
import com.service.TransactionalUserService;
import com.service.exception.ServiceException;
import org.apache.logging.log4j.util.Strings;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServletTest{

    private static final String SURNAME = "surname";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER_DTO = "userDTO";
    private static final String ERRORS = "errors";
    private static final String CODE = "code";
    private static final String CAPTCHA = "captcha";

    CustomAppender customAppender = new CustomAppender();

//    UserRegistrationServlet userServlet = new UserRegistrationServlet();

    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger("CustomAppender");
//
    @Mock
    UserRegistrationServlet userServlet =Mockito.mock(UserRegistrationServlet.class);

    @Mock
    HttpSession session = Mockito.mock(HttpSession.class);

    @Mock
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    @Mock
    ServletConfig config = Mockito.mock(ServletConfig.class);
    @Mock
    HttpServletResponse response= Mockito.mock(HttpServletResponse.class);
    @Mock
    ServletContext context = Mockito.mock(ServletContext.class);
    @Mock
    MemoryUserService serviceMemory = Mockito.mock(MemoryUserService.class);
    @Mock
    TransactionalUserService serviceTransactional = Mockito.mock(TransactionalUserService.class);
    @Mock
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);


    public void init(ServletConfig config) throws ServletException {

        userServlet = (UserRegistrationServlet) config.getServletContext().getAttribute("userServlet");
    }

    @Before
    public void setUp() {
        when(config.getServletContext()).thenReturn(context);
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(context);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(context.getAttribute("memoryUserService")).thenReturn(serviceMemory);
        when(context.getAttribute("transactionalUserService")).thenReturn(serviceTransactional);


    }
    @Test
    public void testServletInit() throws Exception {
        userServlet.init(config);
    }

    @Test
    public void testDoPost() throws ServletException, IOException, ServiceException {
        userServlet.init(config);
        mockGetRequestParams(EMAIL, PASSWORD, NAME, SURNAME, CODE);
        userServlet.doPost(request, response);
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(serviceMemory).add(argument.capture());

        User user = argument.getValue();
        assertEquals(NAME, user.getName());
        assertEquals(SURNAME, user.getSurname());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PASSWORD, user.getPassword());

        verify(serviceMemory).getEmailForEachUser();
        verify(request).setAttribute(eq("statisticMap"), anyMap());
        verify(request).setAttribute(eq(NAME), anyString());
        verify(request).setAttribute(eq(SURNAME), anyString());
        verify(request).setAttribute(eq(EMAIL), anyString());
        verify(request).setAttribute(eq(PASSWORD), anyString());
        verify(request).setAttribute(eq(USER_DTO), anyString());

    }

    @Test(expected = ServletException.class)
    public void testDoPostWithServiceException() throws ServletException, IOException, ServiceException {
        userServlet.init(config);
        mockGetRequestParams(EMAIL, PASSWORD, NAME, SURNAME, CODE);
        when(serviceMemory.add(any(User.class))).thenThrow(ServiceException.class);
        userServlet.doPost(request, response);
    }

    @Test(expected = ServletException.class)
    public void testDoPostWithServiceExceptionOnMemoryService() throws ServletException, IOException, ServiceException {
        userServlet.init(config);
        mockGetRequestParams(EMAIL, PASSWORD, NAME, SURNAME, CODE);
        when(serviceMemory.getEmailForEachUser()).thenThrow(ServiceException.class);
        userServlet.doPost(request, response);
    }

    @Test
    public void testDoPostWithEmptyParamsFromRequest() throws ServletException, IOException {
        userServlet.init(config);
        mockGetRequestParams(Strings.EMPTY, Strings.EMPTY, Strings.EMPTY, Strings.EMPTY, Strings.EMPTY);
        userServlet.doPost(request, response);
//        verify(request).setAttribute(eq(USER_DTO), any(UserDTO.class));
//        verify(request).setAttribute(eq(ERRORS), anyList());

    }

    @Test
    public void testValidateForm() {
        try {
            userServlet.init(config);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        UserDTO userDTO = new UserDTO(EMAIL, PASSWORD, NAME, SURNAME);
        Map<String, String> result = userServlet.validateForm(userDTO, CODE,CAPTCHA);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testValidateFormWithErrors() {
        try {
            userServlet.init(config);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        UserDTO userDTO = new UserDTO(null, null, null, null);
        Map<String, String> result = userServlet.validateForm(userDTO, CODE,CAPTCHA);
        assertFalse(result.isEmpty());
    }

    private void mockGetRequestParams(String name, String surname, String email, String password, String passwordc) {
        when(request.getParameter(EMAIL)).thenReturn(email);
        when(request.getParameter(PASSWORD)).thenReturn(password);
        when(request.getParameter(NAME)).thenReturn(name);
        when(request.getParameter(SURNAME)).thenReturn(surname);
        when(request.getParameter(CODE)).thenReturn(passwordc);
    }
}