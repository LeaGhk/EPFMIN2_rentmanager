package com.epf.rentmanager.dao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleDao {
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle;";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur = ?, modele = ?, nb_places = ? WHERE id = ?;";


	public long create(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, vehicle.getConstructeur());
			ps.setString(2, vehicle.getModele());
			ps.setInt(3, vehicle.getNb_places());
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
		return vehicle.getId();
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			ps.setLong(1, vehicle.getId());

			ps.execute();
			ps.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicle.getId();
	}

	public long update(Vehicle vehicle){
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_VEHICLE_QUERY);
			ps.setString(1, vehicle.getConstructeur());
			ps.setString(2, vehicle.getModele());
			ps.setInt(3, vehicle.getNb_places());
			ps.setLong(4, vehicle.getId());

			ps.executeUpdate();
			ps.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	public Vehicle findById(long id) throws DaoException {
		Vehicle vehicle = new Vehicle();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()){
				vehicle.setId(id);
				vehicle.setConstructeur(rs.getString("constructeur"));
				vehicle.setModele(rs.getString("modele"));
				vehicle.setNb_places(rs.getInt("nb_places"));
			}
			rs.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicle;
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);

			while(rs.next()){
				int id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				String modele = rs.getString("modele");
				int nb_places = rs.getInt("nb_places");

				vehicles.add(new Vehicle(id, constructeur, modele, nb_places));
			}
			rs.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicles;
	}
	
	public int count() throws DaoException, SQLException {
		int n;
		try {
		Connection connection = ConnectionManager.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(COUNT_VEHICLES_QUERY);
		rs.next();
		n = rs.getInt("count");
		rs.close();
		connection.close();
	}catch (SQLException e){
		e.printStackTrace();
		throw new DaoException();
	}
		return n;
	}

	public List<Vehicle> findByIdc(long idc) throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			preparedStatement.setLong(1, idc);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()){
				int id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				String modele = rs.getString("modele");
				int nb_places = rs.getInt("nb_places");
				vehicles.add(new Vehicle(id, constructeur, modele, nb_places));
			}
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicles;
	}

}
