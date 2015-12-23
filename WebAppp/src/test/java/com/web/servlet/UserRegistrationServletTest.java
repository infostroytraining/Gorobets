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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
public class UserRegistrationServletTest {
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER_DTO = "userDTO";
    private static final String ERRORS = "errors";
    private static final String PASSWORD_C = "passwordc";

    CustomAppender customAppender = new CustomAppender();

//    UserRegistrationServlet userServlet = new UserRegistrationServlet();

    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger("CustomAppender");

    @Mock
    UserRegistrationServlet userServlet;// =Mockito.mock(UserRegistrationServlet.class);

    @Mock
    HttpServletRequest request;// = Mockito.mock(HttpServletRequest.class);
    @Mock
    HttpServletResponse response ;//= Mockito.mock(HttpServletResponse.class);
    @Mock
    ServletContext context ;//= Mockito.mock(ServletContext.class);
    @Mock
    MemoryUserService serviceMemory;// = Mockito.mock(MemoryUserService.class);
    @Mock
    TransactionalUserService serviceTransactional;// = Mockito.mock(TransactionalUserService.class);
    @Mock
    RequestDispatcher dispatcher ;//= Mockito.mock(RequestDispatcher.class);

    @Before
    public void init() {
        when(request.getServletContext()).thenReturn(context);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(context.getAttribute("memoryUserService")).thenReturn(serviceMemory);
        when(context.getAttribute("transactionalUserService")).thenReturn(serviceTransactional);

    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        userServlet.doGet(request, response);

    }

    @Test
    public void testDoPost() throws ServletException, IOException, ServiceException {
        mockGetRequestParams(EMAIL, PASSWORD, NAME, SURNAME, PASSWORD_C);
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
        verify(request).getRequestDispatcher("answerToUser.jsp");
    }

    @Test(expected = ServletException.class)
    public void testDoPostWithServiceException() throws ServletException, IOException, ServiceException {
        mockGetRequestParams(EMAIL, PASSWORD, NAME, SURNAME, PASSWORD_C);
        when(serviceMemory.add(any(User.class))).thenThrow(ServiceException.class);
        userServlet.doPost(request, response);
    }

    @Test(expected = ServletException.class)
    public void testDoPostWithServiceExceptionOnMemoryService() throws ServletException, IOException, ServiceException {
        mockGetRequestParams(EMAIL, PASSWORD, NAME, SURNAME, PASSWORD_C);
        when(serviceMemory.getEmailForEachUser()).thenThrow(ServiceException.class);
        userServlet.doPost(request, response);
    }

    @Test
    public void testDoPostWithEmptyParamsFromRequest() throws ServletException, IOException {
        mockGetRequestParams(Strings.EMPTY, Strings.EMPTY, Strings.EMPTY, Strings.EMPTY, Strings.EMPTY);
        userServlet.doPost(request, response);
        verify(request).setAttribute(eq(USER_DTO), any(UserDTO.class));
        verify(request).setAttribute(eq(ERRORS), anyList());
        verify(request).getRequestDispatcher("userErrors.jsp");
    }

    @Test
    public void testValidateForm() {
        UserDTO userDTO = new UserDTO(EMAIL, PASSWORD, NAME, SURNAME);
        List<String> result = userServlet.validateForm(userDTO, PASSWORD_C);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testValidateFormWithErrors() {
        UserDTO userDTO = new UserDTO(null, null, null, null);
        List<String> result = userServlet.validateForm(userDTO, PASSWORD_C);
        assertFalse(result.isEmpty());
    }

    private void mockGetRequestParams(String name, String surname, String email, String password, String passwordc) {
        when(request.getParameter(EMAIL)).thenReturn(email);
        when(request.getParameter(PASSWORD)).thenReturn(password);
        when(request.getParameter(NAME)).thenReturn(name);
        when(request.getParameter(SURNAME)).thenReturn(surname);
        when(request.getParameter(PASSWORD_C)).thenReturn(passwordc);
    }
}