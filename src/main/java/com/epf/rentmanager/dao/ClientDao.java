package com.epf.rentmanager.dao;
import com.epf.rentmanager.model.Client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}

	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client;";


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
				int id = resultSet.getInt(1);
				return id;
			}

			ps.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return 0;
	}
	
	public long delete(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT_QUERY);
			ps.setLong(1, client.getId());
			ps.execute();
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
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return client;
	}

	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<Client>();
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
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}

		return clients;
	}

	public int count() throws DaoException, SQLException {
		Connection connection = ConnectionManager.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(COUNT_CLIENTS_QUERY);
		rs.next();
		return rs.getInt("count");
	}

}
