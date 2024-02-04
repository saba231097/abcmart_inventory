package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.app.service.LoginService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/authUser")
public class LoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String secrets=req.getParameter("secrets");
		LoginService service=new LoginService();
		if(service.authenticateUser(email, secrets)) {
			HttpSession session=req.getSession();
			session.setAttribute("user", email);
			resp.sendRedirect("admin_home.jsp");
			
			
		}else {
			
			req.getSession().setAttribute("loginStatusMSG", "User id or password is wrong!");
			RequestDispatcher dispatcher=req.getRequestDispatcher("index.jsp");
			dispatcher.include(req, resp);
		}
	}
}