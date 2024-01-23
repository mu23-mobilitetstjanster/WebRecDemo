package com.example.webrec.controller;

import com.example.webrec.exception.RegistrationException;
import com.example.webrec.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home/*")
public class HomeServlet extends HttpServlet {

  private AuthService authService = new AuthService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if(req.getPathInfo().equals("/login")) {
      req.getRequestDispatcher("/loginform.jsp").include(req, resp);
    } else {
      req.getRequestDispatcher("/registerform.jsp").include(req, resp);
    }
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    switch(req.getPathInfo()) {
      case "/login": login(req, resp); break;
      case "/register": register(req, resp); break;
    }
  }

  private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    if(authService.find(username, password)) {
      HttpSession session = req.getSession(true);

      session.setAttribute("username", username);
    } else {
      resp.getWriter().println("Invalid user authentication");
    }
  }

  private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    try {
      authService.create(username, password);
    } catch (RegistrationException e) {

      resp.getWriter().println(e.getMessage());
    }
  }
}
