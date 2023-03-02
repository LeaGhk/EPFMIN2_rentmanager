package com.epf.rentmanager.main;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String [] args) throws SQLException {

        try{
            Scanner sc = new Scanner(System.in);

            // Afficher TOUS clients / vehicles / reservations
            List<Client> clients = ClientService.getInstance().findAll();
            System.out.println(clients);
            List<Vehicle> vehicles = VehicleService.getInstance().findAll();
            System.out.println(vehicles);
            List<Reservation> res = ReservationService.getInstance().findAll();
            System.out.println(res);

            int i = ReservationService.getInstance().count();
            System.out.println(i);


//            System.out.println("Entrez l'ID du client : ");
//            long idClient = sc.nextLong();
//            Client client = ClientService.getInstance().findById(idClient);
//            System.out.println(client);

//            System.out.println("Entrez l'ID du véhicule : ");
//            long idVehicle = sc.nextLong();
//            Vehicle vehicle = VehicleService.getInstance().findById(idVehicle);
//            System.out.println(vehicle);

//            List<Reservation> reservations = ReservationDao.getInstance().findAll();
//            System.out.println(reservations);

//            Vehicle v = new Vehicle(1, "Cons", "modl", 4);
//            VehicleService.getInstance().create(v);
//
//            Client c = new Client(1, "dpe", "Chloé", LocalDate.parse("2000-12-12"), "depi@epf.fr");
//            ClientService.getInstance().create(c);

//            ClientDao.getInstance().delete(clients.get(4));
//            VehicleService.getInstance().delete((vehicles.get(4)));
//            vehicles = VehicleService.getInstance().findAll();
//            System.out.println(vehicles);

              // Créer + ajouter réservation --------------------------------------------------
//            Client c = ClientService.getInstance().findById(1);
//            Vehicle v = VehicleService.getInstance().findById(1);
//            Reservation r = new Reservation(1, c, v,LocalDate.parse("2000-12-12"),LocalDate.parse("2000-12-12"));
//            ReservationService.getInstance().create(r);


        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }
}
