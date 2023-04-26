package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class ClientService {

	private final ClientDao clientDao;
	private final ReservationService reservationService;

	public ClientService(ClientDao clientDao, ReservationService reservationService){
		this.clientDao = clientDao;
		this.reservationService = reservationService;
	}


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
			List<Reservation> reservations = reservationService.findAll();
			for(int i=0; i<reservations.size(); i++){
				if(reservations.get(i).getClient().getId() == client.getId()){
					reservationService.delete(reservations.get(i));
				}
			}
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

	public long update(Client client){
		return clientDao.update(client);
	}
	
}
