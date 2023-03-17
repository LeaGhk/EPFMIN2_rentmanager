
package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ClientService clientService;
    private ReservationService reservationService;
    private VehicleService vehicleService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // compter le nombre de voitures différentes que le client a utilisé
            // afficher les voitures du client
            Client c = clientService.findById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("client", c);

            List<Reservation> reservations = reservationService.findAll();
            List<Reservation> resClient = new ArrayList<>();
            for(int i=0; i<reservations.size(); i++){
                if (reservations.get(i).getClient().getId() == c.getId()){
                    resClient.add(reservations.get(i));
                }
            }
            request.setAttribute("reservations", resClient);
            request.setAttribute("nbReservations", resClient.size());

//            List<Vehicle> vehicles = vehicleService.findAll();

            List<Vehicle> vehicles = new ArrayList<>();

            for(int i=0; i<reservations.size(); i++) {
                System.out.println("vehicle  " + reservations.get(i).getVehicle().getId());
//&& !vehicles.contains(reservations.get(i).getVehicle())
                if (reservations.get(i).getClient().getId() == c.getId()) {
                    vehicles.add(reservations.get(i).getVehicle());
                }
            }
//            List<Vehicle> vClient = new ArrayList<>();
//            vClient.add(vehicles.get(0));
//            for(int i=0; i<vehicles.size(); i++){
//                if(vClient.get(i).getId() == vehicles.get(i).getId()){
//                    vClient.add(vehicles.get(i));
//
//                }
//            }

//            List<Vehicle> vClient = new ArrayList<>();
//            List<Vehicle> v = new ArrayList<>();
//            v.add(vehicles.get(1));
//            for(int i=1; i<vehicles.size(); i++){
//                for(int j=i; j<vehicles.size(); j++){
//                    if(vehicles.get(i).getId() == vehicles.get(j).getId()){
//
//                    }
//                }
//            }



            request.setAttribute("vehicles", vehicles);

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
    }

}

