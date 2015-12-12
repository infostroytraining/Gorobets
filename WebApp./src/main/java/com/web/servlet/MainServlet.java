package com.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.User;
import com.dto.UserDTO;
import com.service.UserService;

public class MainServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("date", new Date());
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UserService userService = (UserService) req.getServletContext().getAttribute("userService");

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
//		Image avatar = req.getParameter("language");// find out hoe to hand in a image????
		UserDTO user = new UserDTO(email,password,name,surname);
		List<String> errors = validateForm(user);
		if (!errors.isEmpty()) {
			req.setAttribute("user", user);
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		} else {
			userService.add(new User(email,password,name,surname));
			req.setAttribute("statisticMap", userService.getEmailForEachUser());
			req.setAttribute("name", name);
			req.getRequestDispatcher("user.jsp").forward(req, resp);
		}
	}

	private List<String> validateForm(UserDTO user) {
		List<String> errors = new ArrayList<>();

		if (user.getEmail() == null || user.getEmail().isEmpty()) {
			errors.add("Please, input your name");
		}
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			errors.add("Please, input your name");
		}
		if (user.getName() == null || user.getName().isEmpty()) {
			errors.add("Please, input your name");
		}

		if (user.getSurname() == null || user.getSurname().isEmpty()) {
			errors.add("Please, input your language");
		}
		return errors;
	}
}
