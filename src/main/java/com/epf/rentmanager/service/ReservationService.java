package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;
    public static ReservationService instance;

    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public long create(Reservation reservation) throws ServiceException {
        try {
            return reservationDao.create(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long delete(Reservation reservation) throws ServiceException {
        try {
            return reservationDao.delete(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return reservationDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public Reservation findById(long id) throws DaoException {
        return reservationDao.findById(id);
    }

    public long update(Reservation reservation){
        return reservationDao.update(reservation);
    }

        public int count() throws DaoException, SQLException {
        return reservationDao.count();
    }

    public List<Reservation> findListByIdClient(int id) throws DaoException {
        return reservationDao.findListByIdClient(id);
    }

    public List<Vehicle> findResaVehiclesByIdc(long idc) throws DaoException {
        return reservationDao.findResaVehiclesByIdc(idc);
    }

    public List<Reservation> findResaByClientId(long clientId) throws DaoException {
        return reservationDao.findResaByClientId(clientId);
    }

    public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException, SQLException {
        return reservationDao.findResaByVehicleId(vehicleId);
    }

    public List<Client> findResaClientsByIdv(long idv) throws SQLException, DaoException {
        return reservationDao.findResaClientsByIdv(idv);
    }


}