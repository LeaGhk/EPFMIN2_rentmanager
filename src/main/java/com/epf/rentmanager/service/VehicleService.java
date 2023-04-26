package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class VehicleService {

	private final VehicleDao vehicleDao;
	private final ReservationService reservationService;

	public VehicleService(VehicleDao vehicleDao, ReservationService reservationService){
		this.vehicleDao = vehicleDao;
		this.reservationService = reservationService;
	}

	public long create(Vehicle vehicle) throws ServiceException {
		try{
			return vehicleDao.create(vehicle);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public long delete(Vehicle vehicle) throws ServiceException {
		try{
			List<Reservation> reservations = reservationService.findAll();
			for(int i=0; i<reservations.size(); i++){
				if(reservations.get(i).getVehicle().getId() == vehicle.getId()){
					reservationService.delete(reservations.get(i));
				}
			}
			return vehicleDao.delete(vehicle);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try{
			return vehicleDao.findById(id);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try{
			return vehicleDao.findAll();
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public int count() throws DaoException, SQLException {
		return vehicleDao.count();
	}

    public long update(Vehicle vehicle) {
		return vehicleDao.update(vehicle);
    }
}
