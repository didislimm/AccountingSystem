package com.mironov.controller;

import com.mironov.controller.command.Command;
import com.mironov.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MainController", urlPatterns = "/controller")
public class MainController extends HttpServlet {
    private final CommandProvider commandProvider = new CommandProvider();
    private static final String COMMAND_NAME = "command";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentCommandName;
        Command command;

        req.setCharacterEncoding("UTF-8");

        currentCommandName = req.getParameter(COMMAND_NAME);

        command = commandProvider.getCommand(currentCommandName);
        command.execute(req, resp);
    }
}


