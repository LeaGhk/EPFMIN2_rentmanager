package com.epf.rentmanager.servlet.rents;

import com.epf.rentmanager.exception.*;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.validator.ReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;

@WebServlet("/rents/create")
public class RentCreateServlet extends HttpServlet {

    /**
     *
     */
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("vehicles", this.vehicleService.findAll());
            request.setAttribute("clients", this.clientService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            int veh = parseInt(request.getParameter("car"));
            int cli = parseInt(request.getParameter("client"));
            LocalDate deb = LocalDate.parse(request.getParameter("begin"));
            LocalDate fin = LocalDate.parse(request.getParameter("end"));
            Vehicle v = vehicleService.findById(veh);
            Client c = clientService.findById(cli);
            Reservation r = new Reservation(c, v, deb, fin);

            if(ReservationValidator.carRent7jours(r)){
                throw new CarRent7joursException();
            } else if(ReservationValidator.carUsePerDay(r, reservationService.findAll())) {
                throw new CarUsePerDayException();
            } else if(ReservationValidator.debutIsBeforeFin(r)){
                throw new DebutIsBeforeFin();
            } else if (ReservationValidator.carRent30jours(r, reservationService.findAll())){
                throw new CarRent30joursException();
            }

            reservationService.create(r);
            response.sendRedirect("/rentmanager/rents");
        }
        catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (CarRent7joursException e) {
            request.setAttribute("message", "La location dure 7 jours max");
            request.setAttribute("vehicle", request.getParameter("car"));
            request.setAttribute("client", request.getParameter("client"));
            doGet(request,response);
        } catch (CarUsePerDayException e) {
            request.setAttribute("message", "La voiture est déjà louée à ces dates-là");
            doGet(request,response);
        } catch (DebutIsBeforeFin e) {
            request.setAttribute("message", "La date de début doit être avant la date de fin.");
            doGet(request,response);
        } catch (CarRent30joursException e) {
            request.setAttribute("message", "La voiture ne peut pas être louée plus de 30 jours");
            doGet(request,response);
        }

    }
}
