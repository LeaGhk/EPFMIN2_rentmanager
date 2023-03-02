package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}

	public long create(Vehicle vehicle) throws ServiceException {
		try{
			return VehicleDao.getInstance().create(vehicle);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public long delete(Vehicle vehicle) throws ServiceException {
		try{
			return VehicleDao.getInstance().delete(vehicle);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try{
			return VehicleDao.getInstance().findById(id);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try{
			return VehicleDao.getInstance().findAll();
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public int count() throws DaoException, SQLException {
		return VehicleDao.getInstance().count();
	}
}
