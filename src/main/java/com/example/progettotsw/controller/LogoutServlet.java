package com.example.progettotsw.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("utente");

        request.getSession().invalidate();

        String address = "http://localhost:8080/progettoTSW_war_exploded/home";

        response.sendRedirect(address);
    }
}
