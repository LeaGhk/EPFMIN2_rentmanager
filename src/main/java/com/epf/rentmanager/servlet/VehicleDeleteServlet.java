package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cars/delete")
public class VehicleDeleteServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            VehicleService vehicleService = VehicleService.getInstance();
            vehicleService.delete(vehicleService.findById(id));
            response.sendRedirect("/rentmanager/cars");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

//        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);

    }

}