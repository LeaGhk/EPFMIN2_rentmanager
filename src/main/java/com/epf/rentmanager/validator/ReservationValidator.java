package com.epf.rentmanager.validator;

import com.epf.rentmanager.model.Reservation;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

        if(jours<=7){
            return false;
        }else{
            return true;
        }
    }

    public static boolean debutIsBeforeFin(Reservation reservation){
        if(reservation.getDebut().isAfter(reservation.getFin())){
            return true;
        }else{
            return false;
        }
    }
}
