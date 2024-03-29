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

@WebServlet("/cars/update")
public class VehicleUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Autowired
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    int id = 0;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("idv", id);
            request.setAttribute("vehicle", vehicleService.findById(id));

            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp").forward(request, response);

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String marque = request.getParameter("manufacturer");
            String modele = request.getParameter("modele");
            int seats = parseInt(request.getParameter("seats"));

            Vehicle v = new Vehicle(id, marque, modele, seats);
            vehicleService.update(v);

            response.sendRedirect("/rentmanager/cars");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }


}
