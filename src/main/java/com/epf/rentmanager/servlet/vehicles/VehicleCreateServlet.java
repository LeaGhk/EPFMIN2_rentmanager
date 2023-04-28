package com.epf.rentmanager.servlet.vehicles;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/cars/create")
public class VehicleCreateServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws IOException {
        try {
            String marque = request.getParameter("manufacturer");
            String modele = request.getParameter("modele");
            int seats = parseInt(request.getParameter("seats"));
            Vehicle v = new Vehicle(marque, modele, seats);
            vehicleService.create(v);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/rentmanager/cars");
    }


    }
