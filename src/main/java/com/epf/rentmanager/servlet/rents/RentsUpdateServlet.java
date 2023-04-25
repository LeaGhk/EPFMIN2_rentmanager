package com.epf.rentmanager.servlet.rents;

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
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rents/update")
public class RentsUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    int id = 0;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("idr", id);
            request.setAttribute("reservation", reservationService.findById(id));
            request.setAttribute("vehicles", this.vehicleService.findAll());
            request.setAttribute("clients", this.clientService.findAll());

            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/update.jsp").forward(request, response);

        } catch (DaoException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long idc = Long.valueOf(request.getParameter("client"));
            Long idv = Long.valueOf(request.getParameter("car"));
            LocalDate deb = LocalDate.parse(request.getParameter("begin"));
            LocalDate fin = LocalDate.parse(request.getParameter("end"));

            Vehicle v = vehicleService.findById(idv);
            Client c = clientService.findById(idc);
            Reservation r = new Reservation(id, c, v, deb, fin);
            reservationService.update(r);

            response.sendRedirect("/rentmanager/rents");

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }


}
