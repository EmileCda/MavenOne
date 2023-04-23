package fr.emile.mavenone.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.emile.mavenone.common.IConstant;
import fr.emile.mavenone.entity.BankCard;
import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.connection.DatabaseConnection;
import fr.emile.mavenone.utils.Utils;

public class BankCardDao implements IBankCardDao, IConstant {

	public BankCardDao() {
	}

	public int add(BankCard bankCard) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.getConnection();

			String request = "INSERT INTO `bank_card` (`card_number`,`expiry_date`,`crypto`, `is_valid`,	`is_delete`,`user_id`) VALUES	(? ,? ,? ,? ,?,? )";

			preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, bankCard.getCardNumber());
			preparedStatement.setDate(2, Utils.toSqlDate(bankCard.getExpiryDate()));
			preparedStatement.setString(3, bankCard.getCrypto());
			preparedStatement.setBoolean(4, bankCard.getIsValid());
			preparedStatement.setBoolean(5, bankCard.getIsDeleted());
			preparedStatement.setInt(6, bankCard.getUserId());

			preparedStatement.executeUpdate();
//			Utils.trace(preparedStatement.toString());

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet != null && resultSet.next()) {
				return resultSet.getInt(1); // return only one value (1) Statement.RETURN_GENERATED_KEYS
			}
		} finally {
			this.closeStream(connection, preparedStatement, resultSet);
		}
		return DEFAULT_ID;
	}

//-----------------------------------------------------------------------------
	public BankCard get(int Id) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BankCard bankCard = null;
		try {
			connection = DatabaseConnection.getConnection();
			String request = "SELECT id,card_number, expiry_date,crypto,is_valid,is_delete,user_id FROM bank_card WHERE id = ?";
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setInt(1, Id);

			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			if (resultSet != null && resultSet.next()) {
				bankCard = new BankCard(resultSet.getInt("id"), resultSet.getString("card_number"),
						Utils.toJavaDate(resultSet.getDate("expiry_date")), resultSet.getString("crypto"),
						resultSet.getBoolean("is_valid"), resultSet.getBoolean("is_delete"),
						resultSet.getInt("user_id"));
			}

		} finally {
			this.closeStream(connection, preparedStatement, resultSet);
		}
		return bankCard;
	}

//---------------------------------------------------------------------------------------------------------------------------------
	public List<BankCard> get(User user) throws Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<BankCard> bankCardList = null;
		try {
			connection = DatabaseConnection.getConnection();
			String request = "SELECT id,card_number, expiry_date,crypto,is_valid,is_delete,user_id FROM bank_card WHERE user_id = ?";
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setInt(1, user.getId());

			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			if (resultSet != null) {
				bankCardList = new ArrayList<BankCard>();

				while (resultSet.next()) {
					BankCard bankCard = new BankCard(resultSet.getInt("id"), resultSet.getString("card_number"),
							Utils.toJavaDate(resultSet.getDate("expiry_date")), resultSet.getString("crypto"),
							resultSet.getBoolean("is_valid"), resultSet.getBoolean("is_delete"),
							resultSet.getInt("user_id"));

					bankCardList.add(bankCard);
				}
			}
		} finally {
			this.closeStream(connection, preparedStatement, resultSet);
		}
		return bankCardList;
	}

//---------------------------------------------------------------------------------------------------------------------------------
	public List<BankCard> get() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<BankCard> bankCardList = null;
		try {
			connection = DatabaseConnection.getConnection();
			String request = "SELECT id,card_number, expiry_date,crypto,is_valid,is_delete,user_id FROM bank_card";
			preparedStatement = connection.prepareStatement(request);

			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			if (resultSet != null) {
				bankCardList = new ArrayList<BankCard>();

				while (resultSet.next()) {
					BankCard bankCard = new BankCard(resultSet.getInt("id"), resultSet.getString("card_number"),
							Utils.toJavaDate(resultSet.getDate("expiry_date")), resultSet.getString("crypto"),
							resultSet.getBoolean("is_valid"), resultSet.getBoolean("is_delete"),
							resultSet.getInt("user_id"));

					bankCardList.add(bankCard);
				}
			}
		} finally {
			this.closeStream(connection, preparedStatement, resultSet);
		}
		return bankCardList;
	}

//---------------------------------------------------------------------------------------------------------------------------------
	public int Invalidate(BankCard bankCard) throws Exception {

		return this.Invalidate(bankCard, !DEFAULT_IS_VALIDE);
	}
//---------------------------------------------------------------------------------------------------------------------------------

	public int Invalidate(BankCard bankCard, boolean undo) throws Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		try {

			connection = DatabaseConnection.getConnection();

			String request = "UPDATE `bank_card` SET `is_valid` = ? WHERE `id` = ?;";
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setBoolean(1, undo);
			preparedStatement.setInt(2, bankCard.getId());
//			Utils.trace(preparedStatement.toString());
			result = preparedStatement.executeUpdate();
		} finally {
			this.closeStream(connection, preparedStatement);
		}
		return result;

	}
//---------------------------------------------------------------------------------------------------------------------------------
	public int delete(BankCard bankCard) throws Exception {
		return this.delete(bankCard, !DEFAULT_IS_DELETE);
	}

//---------------------------------------------------------------------------------------------------------------------------------
	public int delete(BankCard bankCard, boolean undo) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		try {

			connection = DatabaseConnection.getConnection();

			String request = "UPDATE `bank_card` SET `is_delete` = ?  WHERE `id` = ?;";
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setBoolean(1, undo);
			preparedStatement.setInt(2, bankCard.getId());
			result = preparedStatement.executeUpdate();

		} finally {
			this.closeStream(connection, preparedStatement);
		}

		return result;

	}

	//---------------------------------------------------------------------------------------------------------------------------------
	public int delete(User user) throws Exception {
		return this.delete(user, !DEFAULT_IS_DELETE);

	}

	//---------------------------------------------------------------------------------------------------------------------------------
	public int delete(User user, boolean undo) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		try {

			connection = DatabaseConnection.getConnection();

			String request = "UPDATE `bank_card` SET `is_delete` = ?  WHERE `user_id` = ?;";
			preparedStatement = connection.prepareStatement(request);

			preparedStatement.setBoolean(1, undo);
			preparedStatement.setInt(2, user.getId());
			result = preparedStatement.executeUpdate();

		} finally {
			this.closeStream(connection, preparedStatement);
		}

		return result;

	}

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

	// -----------------------------------------------------------------------------
	private void closeStream(Connection connection, PreparedStatement preparedStatement) throws Exception {

		closeStream(connection, preparedStatement, null);

	}

}
