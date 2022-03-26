package com.br.tecnodev.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/createCategory")
public class CreateCategory extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));
        Integer orderInSystem = Integer.valueOf(request.getParameter("orderInSystem"));
        String imageUrl = request.getParameter("imageUrl");
        String colorCode = request.getParameter("colorCode");
    }
}
