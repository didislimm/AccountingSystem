package com.mironov.controller.command.impl;

import com.mironov.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckValuesCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session=request.getSession();
        session.setAttribute("valueOfFlats",request.getParameter("valueOfFlats"));
        session.setAttribute("numberOfHouse",request.getParameter("numberOfHouse"));
        session.setAttribute("numberOfFloors",request.getParameter("valueOfFloors"));
        request.getRequestDispatcher("/jsp/create-house-2.jsp").forward(request,response);
    }
}
