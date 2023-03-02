package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;

public class ReservationService {

    private final ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }


    public long create(Reservation reservation) throws ServiceException {
        try{
            return reservationDao.getInstance().create(reservation);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long delete(Reservation reservation) throws ServiceException {
        try{
            return reservationDao.getInstance().delete(reservation);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try{
            return reservationDao.getInstance().findAll();
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public int count() throws DaoException, SQLException {
        return reservationDao.getInstance().count();
    }

}
