package com.mironov.controller.filter;

import com.mironov.repository.impl.HouseRepositoryImpl;
import com.mironov.services.HouseService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns ={"/jsp/create-house-2.jsp"})
public class HouseNumberFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        int numberOfHouse=Integer.parseInt((String) servletRequest.getAttribute("numberOfHouse"));
        List<Integer> allNumberOfHouses = HouseService.getInstance(HouseRepositoryImpl.getInstance()).getAllNumberOfHouses();
        if (!allNumberOfHouses.contains(numberOfHouse)){
            if (servletRequest instanceof HttpServletRequest) {
                String url = ((HttpServletRequest) servletRequest).getRequestURL().toString();
                servletRequest.getRequestDispatcher(url).forward(servletRequest,servletResponse);
            }
        }
        else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
