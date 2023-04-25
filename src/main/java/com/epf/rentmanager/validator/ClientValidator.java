package com.epf.rentmanager.validator;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;

@Component
public class ClientValidator {
    @Autowired
    private static ClientService clientService;

    public static boolean userIs18years(Client client){
        Period period = Period.between(client.getNaissance(), LocalDate.now());
        int years = Math.abs(period.getYears());

        if(years >= 18){
            return false;
        } else {
            return true;
        }
    }

    public static boolean verifMailUtilise(Client client, List<Client> clients) throws ServiceException {
        boolean b = false;
        for(int i=0; i<clients.size(); i++){
            if(clients.get(i).getEmail().equals(client.getEmail())){
                b = true;
                break;
            }
        }
        return b;
    }
}
