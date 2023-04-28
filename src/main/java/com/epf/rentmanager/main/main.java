//cd c/Users/Ghanotakis/Documents/EPF/EPF_2023_cours_S8/1_Java/rentmanager
/*
A faire
- Modifier user / vehicle
- Voir les détails user / vehicle
- Faire entièrement reservations

*/

package com.epf.rentmanager.main;
import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    @Autowired

    public static void main(String [] args) throws SQLException, ServiceException {

        Scanner sc = new Scanner(System.in);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        ReservationService reservationService = context.getBean(ReservationService.class);
        ClientValidator clientValidator = new ClientValidator();


        // Afficher TOUS clients / vehicles / reservations --------------------------------------------------
//            List<Client> clients = clientService.findAll();
//            System.out.println(clients);
//            List<Vehicle> vehicles = VehicleService.getInstance().findAll();
//            System.out.println(vehicles);
//            List<Reservation> res = reservationService.findAll();
//            System.out.println(res);

//
//
//            int i = ReservationService.getInstance().count();
//            System.out.println(i);
//            System.out.println(ClientService.getInstance().count());


        // Trouver un client --------------------------------------------------
//            System.out.println("Entrez l'ID du client : ");
//            long idClient = sc.nextLong();
//            List<Client> clients = new ArrayList<>();
//            clients.add(clientService.findById(2));
//            clients.add(clientService.findById(3));
//            Client client = clientService.findById(3);
        // Trouver une voiture --------------------------------------------------
//            System.out.println("Entrez l'ID du véhicule : ");
//            long idVehicle = sc.nextLong();
//            Vehicle vehicle = VehicleService.getInstance().findById(idVehicle);
//            System.out.println(vehicle);

        // Créer une voiture et un client --------------------------------------------------
//            Vehicle v = new Vehicle(1, "Cons", "modl", 4);
//            VehicleService.getInstance().create(v);
//            Client c = new Client("HHHHHA", "Chloé", LocalDate.now(), "depi@epf.fr");
//            ClientService.getInstance().create(c);

//            ClientDao.getInstance().delete(clients.get(4));
//            VehicleService.getInstance().delete((vehicles.get(4)));
//            vehicles = VehicleService.getInstance().findAll();
//            System.out.println(vehicles);


        // Créer + ajouter réservation --------------------------------------------------
//            Client c = ClientService.getInstance().findById(1);
//            Vehicle v = VehicleService.getInstance().findById(3);
//            Reservation r = new Reservation(1, c, v,LocalDate.parse("2000-03-12"),LocalDate.parse("2000-12-12"));
//            ReservationService.getInstance().create(r);


//        } catch (ServiceException e) {
//            e.printStackTrace();
//        } catch (DaoException e) {
//            throw new RuntimeException(e);
//        }

    }
}
