package com.mironov.controller.command.impl;

import com.mironov.controller.command.Command;
import com.mironov.controller.filter.HouseNumberBusyFilter;
import com.mironov.model.House;
import com.mironov.repository.impl.HouseRepositoryImpl;
import com.mironov.services.HouseService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveHouseCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int numberOfHouse= Integer.parseInt(request.getParameter("numberOfHouse"));
        HouseService houseService=HouseService.getInstance(HouseRepositoryImpl.getInstance());
        House house=houseService.findHouseByNumber(numberOfHouse);
        HttpSession session= request.getSession();
        session.setAttribute("house",house);
        request.getRequestDispatcher("/jsp/remove-house-2.jsp").forward(request,response);
        houseService.removeByKey(numberOfHouse);

    }
}
