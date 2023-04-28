package com.epf.rentmanager.validator;

import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
@Component
public class ReservationValidator {

    public static boolean carUsePerDay(Reservation reservation, List<Reservation> reservations){
        boolean b = false;
        LocalDate debutRent = reservation.getDebut();
        LocalDate finRent = reservation.getFin();
        for(int i=0; i<reservations.size();i++) {
            LocalDate debutRentList = reservations.get(i).getDebut();
            LocalDate finRentList = reservations.get(i).getFin();
            if(reservation.getVehicle().getId() == reservations.get(i).getVehicle().getId()){
                if(
                        (debutRent.isAfter(debutRentList) && debutRent.isBefore(finRentList)) ||
                        (finRent.isAfter(debutRentList) && finRent.isBefore(finRentList)) ||
                        debutRent.isEqual(debutRentList) || debutRent.isEqual(finRentList) ||
                        finRent.isEqual(debutRentList) || finRent.isEqual(finRentList) ||
                        (debutRentList.isAfter(debutRent) && debutRentList.isBefore(finRent)) ||
                        (finRentList.isAfter(debutRent) && finRentList.isBefore(finRent))
                ){
                    b = true;
                    break;
                }
            }
        }
        return b;
    }

    public static boolean carRent7jours(Reservation reservation){
        long jours = reservation.getDebut().until(reservation.getFin(), ChronoUnit.DAYS);
        return jours >= 7;
    }

    public static boolean debutIsBeforeFin(Reservation reservation){
        return reservation.getDebut().isAfter(reservation.getFin());
    }

    // List reservations = findResaByVehicleId
    public static boolean carRent30jours(Reservation reservation, List<Reservation> reservations){
        reservations.add(reservation);
        Collections.sort(reservations, Reservation.ComparatorDate);

        long jours = reservations.get(0).getDebut().until(reservations.get(0).getFin(), ChronoUnit.DAYS);
        boolean b = false;

        for(int i=1; i<reservations.size(); i++){
            if(reservations.get(i-1).getFin().plusDays(1).isEqual(reservations.get(i).getDebut())){
                long nb = reservations.get(i).getDebut().until(reservations.get(i).getFin().plusDays(1), ChronoUnit.DAYS);
                jours = jours + nb;
                if(jours>=30){
                    b = true;
                    break;
                }
            }else{
                jours = 0;
            }
        }
        return b;
    }
}
