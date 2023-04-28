
package com.epf.rentmanager.servlet.users;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long idc = Integer.parseInt(request.getParameter("id"));
            Client c = clientService.findById(idc);
            List<Reservation> listRes = reservationService.findResaByClientId(idc);
            List<Vehicle> listVeh = reservationService.findResaVehiclesByIdc(idc);

            request.setAttribute("client", c);
            request.setAttribute("reservations", listRes);
            request.setAttribute("nbReservations", listRes.size());
            request.setAttribute("vehicles", listVeh);
            request.setAttribute("nbVehicles", listVeh.size());

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }


        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
    }

}

