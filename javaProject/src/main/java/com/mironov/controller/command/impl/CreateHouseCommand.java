package com.mironov.controller.command.impl;

import com.mironov.controller.command.Command;
import com.mironov.model.House;
import com.mironov.repository.impl.HouseRepositoryImpl;
import com.mironov.services.HouseService;
import com.mironov.util.HouseGenerator;
import com.mysql.cj.xdevapi.JsonString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateHouseCommand implements Command {
//TODO
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session=request.getSession();
        int valueOfFlats= Integer.parseInt((String) session.getAttribute("valueOfFlats"));
        ArrayList<Double> squaresFlats=new ArrayList<>();
        for(int i=1;i<=valueOfFlats;i++){
            squaresFlats.add(Double.parseDouble(request.getParameter("square"+i)));
        }
        HouseGenerator houseGenerator=HouseGenerator.getInstance(Integer.parseInt((String) session.getAttribute("numberOfFloors")));
        House house= houseGenerator.createRandomHouse(squaresFlats);
        house.setNumberOfHouse(Integer.parseInt((String) session.getAttribute("numberOfHouse")));
        HouseService houseService=HouseService.getInstance(HouseRepositoryImpl.getInstance());
        houseService.save(house);
        session.invalidate();
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
