package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
//import jdk.vm.ci.meta.Local;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

@WebServlet("/rents/create")
public class RentCreateServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ReservationService reservationService = ReservationService.getInstance();
    private VehicleService vehicleService = VehicleService.getInstance();
    private ClientService clientService = ClientService.getInstance();

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

            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            Date deb = DateFor.parse(request.getParameter("begin"));

//            String deb = request.getParameter("begin");
//            deb = deb.replace('/', '-');
//           LocalDate debu = LocalDate.parse(deb);

            System.out.println("AAAAA "+deb);
//            LocalDate fin = LocalDate.parse(request.getParameter("end"));
//
//            Vehicle v = vehicleService.findById(veh);
//            Client c = clientService.findById(cli);
//
//            Reservation r = new Reservation(c, v, debu, fin);
//            reservationService.create(r);
//        }
//        catch (ServiceException e) {
//            //catch le probleme pousser dans la requete et afficher sur le site le prb
//            // utilisateur entre un mail ou un numéro invalide pour les voitchur
//            throw new RuntimeException(e);
//        }

        this.doGet(request, response);

//        response.sendRedirect("/rentmanager/users");
    } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
