package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

	private ClientDao clientDao;
	private VehicleDao vehicleDao;

	public ReservationDao(ClientDao clientDao, VehicleDao vehicleDao) {
		this.clientDao = clientDao;
		this.vehicleDao = vehicleDao;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservation;";

	public long create(Reservation reservation) throws DaoException {
		try {

			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, reservation.getClient().getId());
			ps.setLong(2, reservation.getVehicle().getId());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();

			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				return id;
			}

			ps.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservation.getId();
	}
	
	public long delete(Reservation reservation) throws DaoException {
		return 0;
	}
	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			preparedStatement.setLong(1, clientId);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()){
				long id = rs.getLong("id");
				Client client = clientDao.findById(clientId);
				long vehicle_id = rs.getLong("vehicle_id");
				Vehicle vehicle = vehicleDao.findById(vehicle_id);
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservations.add(new Reservation(id, client, vehicle, debut, fin));
			}
			connection.close();
		} catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}

	public List<Vehicle> findByIdc(long idc) throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		List<Reservation> reservations = findResaByClientId(idc);
		for(int i=0; i<reservations.size(); i++){
//			if(vehicles.contains(reservations.get(i).getVehicle())==false){
			vehicles.add(reservations.get(i).getVehicle());
//			}
		}
		for(int i=0; i<vehicles.size()-1; i++){
			for(int j=i+1; j<vehicles.size(); j++){
				if(vehicles.get(i).getId() == vehicles.get(j).getId()){
					vehicles.remove(j);
				}
			}
		}
		return vehicles;
	}
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException, SQLException {
		// à tester, pas sur que le chiffre soit 1 pr prepared statement
		List<Reservation> reservations = new ArrayList<Reservation>();

		try {
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
		preparedStatement.setLong(1, vehicleId);
		ResultSet rs = preparedStatement.executeQuery();

		while(rs.next()){
			long id = rs.getLong("id");
			long client_id = rs.getLong("client_id");
			Client client = clientDao.findById(client_id);
			Vehicle vehicle = vehicleDao.findById(vehicleId);
			LocalDate debut = rs.getDate("debut").toLocalDate();
			LocalDate fin = rs.getDate("fin").toLocalDate();
			reservations.add(new Reservation(id, client, vehicle, debut, fin));
		}
		connection.close();

		} catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);

			while(rs.next()){
				int id = rs.getInt("id");
				long idClient = rs.getLong("client_id");
				long idVehicle = rs.getLong("vehicle_id");
				// il faut changer ClientService.getInstance par ca ?
				Client client = clientDao.findById(idClient);
				Vehicle vehicle = vehicleDao.findById(idVehicle);
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client, vehicle, debut, fin));
			}
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}

		return reservations;
	}

	public int count() throws DaoException, SQLException {
		int n;
		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(COUNT_RESERVATIONS_QUERY);
			rs.next();
			n = rs.getInt("count");
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return n;
	}

	//je crois que c'est exactement la meme chose que findResaByIdClient mais sans la query ?
	public List<Reservation> findListByIdClient(int id) throws DaoException {
		List<Reservation> reservations = findAll();
		List<Reservation> resClient = new ArrayList<>();
		for(int i=0; i<reservations.size(); i++){
			if (reservations.get(i).getClient().getId() == id){
				resClient.add(reservations.get(i));
			}
		}
		return resClient;
	}
}
