package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;
@Service
public class ClientService {

	private ClientDao clientDao;

	public ClientService(ClientDao clientDao){
		this.clientDao = clientDao;
	}

//	private ClientService() {
//		this.clientDao = ClientDao.getInstance();
//	}
//
//	public static ClientService getInstance() {
//		if (instance == null) {
//			instance = new ClientService();
//		}
//		return instance;
//	}
	
	
	public long create(Client client) throws ServiceException {
		try{
			return clientDao.create(client);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public void delete(Client client) throws ServiceException {
		try{
			clientDao.delete(client);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	public Client findById(long id) throws ServiceException {
		try{
			return clientDao.findById(id);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Client> findAll() throws ServiceException {
		try{
			return clientDao.findAll();
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public int count() throws DaoException, SQLException {
		return clientDao.count();
	}

	public void update(Client client){
		clientDao.update(client);
	}
	
}
