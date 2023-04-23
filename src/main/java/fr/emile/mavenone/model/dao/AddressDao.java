package fr.emile.mavenone.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.emile.mavenone.common.IConstant;
import fr.emile.mavenone.entity.Address;
import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.connection.DatabaseConnection;

public class AddressDao implements IAddressDao, IConstant {

	
	public AddressDao() {
	}
//-----------------------------------------------------------------------------	

	public int add(Address address) throws Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "INSERT INTO address(number, street, city,zip_code,user_id) VALUES(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, address.getNumber());
			preparedStatement.setString(2, address.getStreet());
			preparedStatement.setString(3, address.getCity());
			preparedStatement.setString(4, address.getZipCode());
			preparedStatement.setInt(5, address.getIdUser());

			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet != null && resultSet.next()) {
				return resultSet.getInt(1); // return only one value (1) Statement.RETURN_GENERATED_KEYS
			}
		} finally {
			this.closeStream(connection, preparedStatement, resultSet);
		}
		return DEFAULT_ID;
	}

	// -----------------------------------------------------------------------------
	public List<Address> get() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Address> addressList = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "SELECT * FROM address";
			preparedStatement = connection.prepareStatement(requete);

			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			if (resultSet != null) {
				addressList = new ArrayList<Address>();
				while (resultSet.next()) {
					Address address = new Address(resultSet.getInt("id"), resultSet.getString("number"),
							resultSet.getString("street"), resultSet.getString("city"), resultSet.getString("zip_code"),
							resultSet.getInt("user_id"));

					addressList.add(address);
				}
			}
		} finally {
			this.closeStream(connection, preparedStatement, resultSet);
		}
		return addressList;
	}

// -----------------------------------------------------------------------------
	public List<Address> get(User user) throws Exception {

		return this.get(user.getId());

	}

	// -----------------------------------------------------------------------------
	public List<Address> get(int userId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Address> addressList = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "SELECT * FROM address WHERE user_id=?";

			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setInt(1, userId);

			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			if (resultSet != null) {
				addressList = new ArrayList<Address>();
				while (resultSet.next()) {
					Address address = new Address(resultSet.getInt("id"), resultSet.getString("number"),
							resultSet.getString("street"), resultSet.getString("city"), resultSet.getString("zip_code"),
							resultSet.getInt("user_id"));

					addressList.add(address);
				}
			}
		} finally {

			this.closeStream(connection, preparedStatement, resultSet);
		}
		return addressList;
	}

	// -----------------------------------------------------------------------------
	public Address getById(int id) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Address address = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "SELECT `id`, `number`, `street`, `city`," + "`zip_code`, `user_id` "
					+ " FROM address WHERE id=?";

			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setInt(1, id);
//			Utils.trace(preparedStatement.toString());
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			if (resultSet != null && resultSet.next()) {
				address = new Address(resultSet.getInt("id"), resultSet.getString("number"),
						resultSet.getString("street"), resultSet.getString("city"), resultSet.getString("zip_code"),
						resultSet.getInt("user_id"));

			}
		} finally {

			this.closeStream(connection, preparedStatement, resultSet);
		}
		return address;
	}

// -----------------------------------------------------------------------------
	public int Update(Address address) throws Exception {
		Connection connection = null;
		int result = 0 ; 

		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "UPDATE address SET  number = ?, street = ?, city = ?, zip_code= ?, user_id = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(requete);

			preparedStatement.setString(1, address.getNumber());
			preparedStatement.setString(2, address.getStreet());
			preparedStatement.setString(3, address.getCity());
			preparedStatement.setString(4, address.getZipCode());
			preparedStatement.setInt(5, address.getIdUser());
			preparedStatement.setInt(6, address.getId());

			result =preparedStatement.executeUpdate();
		} finally {
			this.closeStream(connection, preparedStatement);
		}
		return result; 
	}

// -----------------------------------------------------------------------------
	public int delete(int id) throws Exception {
		Connection connection = null;
		int result = 0 ; 
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "DELETE FROM address WHERE id = ?";
			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();

		} finally {

			this.closeStream(connection, preparedStatement);
		}
		return result ; 

	}

	// -----------------------------------------------------------------------------

	// ---------------processing methode ---------------------
	private void closeStream(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
			throws Exception {

		if (resultSet != null && !resultSet.isClosed()) {
			resultSet.close();
		}
		if (preparedStatement != null && !preparedStatement.isClosed()) {
			preparedStatement.close();
		}
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}

	}

//-----------------------------------------------------------------------------
	private void closeStream(Connection connection, PreparedStatement preparedStatement) throws Exception {

		closeStream(connection, preparedStatement, null);

	}

}
