package fr.emile.mavenone.entity;

import java.util.Date;

import fr.emile.mavenone.common.IConstant;
import fr.emile.mavenone.utils.Utils;

public class BankCard implements IConstant {
	private int id;
	private String cardNumber;
//	private String expiryDate ; 
	private Date expiryDate;
	private String crypto;
	private boolean isValid;
	private boolean isDeleted;
	private int userId;

	// ------------------------ constructor --------------
	/**
	 * @param id
	 * @param cardNumber
	 * @param expiryDate
	 * @param crypto
	 * @param isValid
	 * @param isdeleted
	 */
	public BankCard(int id, String cardNumber, Date expiryDate, String crypto, boolean isValid, boolean isdeleted,
			int userId) {
		this.setId(id);
		this.setCardNumber(cardNumber);
		this.setExpiryDate(expiryDate);
		this.setCrypto(crypto);
		this.setIsValid(isValid);
		this.setIsDeleted(isdeleted);
		this.setUserId(userId);
	}

	public BankCard(String cardNumber, Date expiryDate, String crypto, int userId) {
		this(DEFAULT_ID, cardNumber, expiryDate, crypto, DEFAULT_IS_VALIDE, DEFAULT_IS_DELETE, userId);
	}

	public BankCard(String cardNumber, Date expiryDate, String crypto) {
		this(DEFAULT_ID, cardNumber, expiryDate, crypto, DEFAULT_IS_VALIDE, DEFAULT_IS_DELETE, DEFAULT_ID);
	}

	public BankCard() {
		this(DEFAULT_ID, DEFAULT_BANK_CARD_NUMBER, NULL_DATE, DEFAULT_BANK_CARD_CRYPTO, DEFAULT_IS_VALIDE,
				DEFAULT_IS_DELETE, DEFAULT_ID);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCrypto() {
		return crypto;
	}

	public void setCrypto(String crypto) {
		this.crypto = crypto;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isdeleted) {
		this.isDeleted = isdeleted;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {

		String result = String.format("BC(%d) %s ", this.getId(), getCardNumber());

		try {
			result += String.format("exp %s ", Utils.date2String(this.getExpiryDate(), "MM/yy"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		result += String.format("[%s] {%s,%s}  userId : %d", getCrypto(), getIsValid() ? "Valid" : "inValid",
				getIsDeleted() ? "deleted" : "in use", getUserId());

		return result;
	}
}
