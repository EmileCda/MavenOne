package fr.emile.mavenone.model.dao;

import java.util.List;

import fr.emile.mavenone.common.IConstant;
import fr.emile.mavenone.entity.Address;
import fr.emile.mavenone.entity.BankCard;
import fr.emile.mavenone.entity.Order;
import fr.emile.mavenone.entity.User;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fr.emile.mavenone.model.connection.DatabaseConnection;
import fr.emile.mavenone.utils.Utils;

public class UserDao implements IUserDao, IConstant {

	public UserDao() {
	}

//-----------------------------------------------------------------------------	

	public int add(User user) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "INSERT INTO user(firstname, lastname, date_of_birth) VALUES(?,?,?)";
			preparedStatement = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setDate(3, Utils.toSqlDate(user.getDateOfBirth()));

			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet != null && resultSet.next()) {
				return resultSet.getInt(1);
			}
		} finally {
			this.closeStream(connection, preparedStatement, resultSet);
		}
		return DEFAULT_ID;

	}
//-----------------------------------------------------------------------------	

	public List<User> get() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> userList = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "SELECT * FROM user";
			preparedStatement = connection.prepareStatement(requete);

			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			if (resultSet != null) {
				final IAddressDao myAddressDao = new AddressDao();
				final IBankCardDao myBankCardDao = new BankCardDao();
				final IOrderDao myOrderDao = new OrderDao();
				userList = new ArrayList<User>();
				List<Address> addressList = new ArrayList<Address>();
				List<BankCard> bankCardList = new ArrayList<BankCard>();
				List<Order> orderList = new ArrayList<Order>();

				while (resultSet.next()) {
					User user = new User(resultSet.getInt("id"), resultSet.getString("firstname"),
							resultSet.getString("lastname"), Utils.toJavaDate(resultSet.getDate("date_of_birth")));

					addressList = myAddressDao.get(user.getId());
					user.addAddress(addressList);
					bankCardList = myBankCardDao.get(user);
					user.addBankCard(bankCardList);

					orderList = myOrderDao.get(user);
					user.addOrder(orderList);

					userList.add(user);
				}
			}
		} finally {
			this.closeStream(connection, preparedStatement, resultSet);
		}
		return userList;
	}

//-----------------------------------------------------------------------------	

	public User get(int id) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "SELECT * FROM user WHERE id = ?";
			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setInt(1, id);
//			Utils.trace(prepareStatement.toString());

			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			if (resultSet != null && resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("firstname"),
						resultSet.getString("lastname"), Utils.toJavaDate(resultSet.getDate("date_of_birth")));

				List<Address> addressList = new ArrayList<Address>();
				List<BankCard> bankCardList = new ArrayList<BankCard>();
				List<Order> orderList = new ArrayList<Order>();
				final IAddressDao myAddressDao = new AddressDao();
				final IBankCardDao myBankCardDao = new BankCardDao();
				final IOrderDao myOrderDao = new OrderDao();

				addressList = myAddressDao.get(user.getId());
				user.addAddress(addressList);
				bankCardList = myBankCardDao.get(user);
				user.addBankCard(bankCardList);
				orderList = myOrderDao.get(user);
				user.addOrder(orderList);
			}
		} finally {
			this.closeStream(connection, preparedStatement, resultSet);
		}
		return user;
	}
//-----------------------------------------------------------------------------	

	public int Update(User user) throws Exception {
		Connection connection = null;
		int result = 0;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "UPDATE user SET firstname = ?, lastname = ?, date_of_birth = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setDate(3, Utils.toSqlDate(user.getDateOfBirth()));
			preparedStatement.setInt(4, user.getId());

			result = preparedStatement.executeUpdate();

		} finally {

			this.closeStream(connection, preparedStatement);
		}
		return result;
	}

//-----------------------------------------------------------------------------	
	public int delete(User user) throws Exception {
		return delete(user.getId());
	}

//-----------------------------------------------------------------------------	
	public int delete(int id) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseConnection.getConnection();
			String requete = "DELETE FROM user WHERE id = ?";
			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();

		} finally {

			this.closeStream(connection, preparedStatement);
		}
		return result;

	}
//-----------------------------------------------------------------------------	

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
