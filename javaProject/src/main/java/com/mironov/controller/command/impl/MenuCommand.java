package com.mironov.controller.command.impl;

import com.mironov.controller.command.Command;
import com.mironov.repository.impl.HouseRepositoryImpl;
import com.mironov.services.HouseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MenuCommand implements Command {
    HouseService houseService= HouseService.getInstance(HouseRepositoryImpl.getInstance());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String numbers=houseService.getAllNumberOfHouses().toString();
        request.setAttribute("numbersOfHouses",numbers);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
