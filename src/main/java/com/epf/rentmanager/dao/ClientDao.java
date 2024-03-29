package com.epf.rentmanager.dao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDao {

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id = ?;";

	public long create(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setString(4, String.valueOf(client.getNaissance()));

			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();

			if (resultSet.next()) {
				return resultSet.getInt(1);
			}

			ps.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return 0;
	}
	
	public void delete(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT_QUERY);
			ps.setLong(1, client.getId());
			ps.execute();

			ps.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public long update(Client client) throws DaoException{
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_CLIENT_QUERY);
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setString(4, String.valueOf(client.getNaissance()));
			ps.setLong(5, client.getId());

			ps.executeUpdate();
			ps.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return 0;
	}

	public Client findById(long id) throws DaoException {
		Client client = new Client();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT_QUERY);
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()){
				client.setId(id);
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setNaissance(rs.getDate("naissance").toLocalDate());
				client.setEmail(rs.getString("email"));
			}
			rs.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return client;
	}

	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

			while(rs.next()){
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				LocalDate naissance = rs.getDate("naissance").toLocalDate();
				String email = rs.getString("email");

				clients.add(new Client(id, nom, prenom, naissance, email));
			}
			rs.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}

		return clients;
	}

	public int count() throws DaoException, SQLException {
		int n;
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(COUNT_CLIENTS_QUERY);
			rs.next();
			n = rs.getInt("count");
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return n;
	}

}
