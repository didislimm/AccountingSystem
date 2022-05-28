package com.mironov.controller.filter;

import com.mironov.repository.impl.HouseRepositoryImpl;
import com.mironov.services.HouseService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/jsp/output-house-2.jsp", "/jsp/remove-house-2.jsp"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class HouseNumberBusyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        int numberOfHouse = (Integer.parseInt(servletRequest.getParameter("numberOfHouse")));
        List<Integer> allNumberOfHouses = HouseService.getInstance(HouseRepositoryImpl.getInstance()).getAllNumberOfHouses();
        if (!allNumberOfHouses.contains(numberOfHouse)) {
            if (servletRequest instanceof HttpServletRequest) {
                String url = ((HttpServletRequest) servletRequest).getRequestURI();
                url = url.replace("2.jsp", "1.jsp");
                servletRequest.getRequestDispatcher(url).forward(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
