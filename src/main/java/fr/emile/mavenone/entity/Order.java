package fr.emile.mavenone.entity;

import java.sql.ResultSet;
import java.util.Date;

import fr.emile.mavenone.common.IConstant;
import fr.emile.mavenone.enums.DeliveryMethod;
import fr.emile.mavenone.enums.OrderStatus;
import fr.emile.mavenone.model.dao.AddressDao;
import fr.emile.mavenone.model.dao.BankCardDao;
import fr.emile.mavenone.model.dao.IAddressDao;
import fr.emile.mavenone.model.dao.IBankCardDao;
import fr.emile.mavenone.utils.Utils;

public class Order implements IConstant {
	//---------------attribut ---------------------
		private int id;
		private int number;
		private Date createDate;
		private Date deliveryDate;
		private Date cancellationDate;
		private DeliveryMethod deliveryMethod;
		private int deliveryAddressId;
		private int billingAddressId;
		private int bankCardUsedId;
		private OrderStatus status;
		private int userId;
		private boolean isDeleted;

		// ------------------------ constructor --------------
		/**
		 * @param id
		 * @param number
		 * @param createDate
		 * @param deliveryDate
		 * @param cancellationDate
		 * @param deliveryMethod
		 * @param deliveryAddressId
		 * @param billingAddressId
		 * @param bankCardUsedId
		 * @param status
		 * @param userId
		 */
		public Order(int id, int number, Date createDate, Date deliveryDate, Date cancellationDate,
				DeliveryMethod deliveryMethod, int deliveryAddressId, int billingAddressId, int bankCardUsedId,
				OrderStatus status, boolean isDeleted,int userId) {
			this.setId(id);
			this.setNumber(number);
			this.setCreateDate(createDate);
			this.setDeliveryDate(deliveryDate);
			this.setCancellationDate(cancellationDate);
			this.setDeliveryMethod(deliveryMethod);
			this.setDeliveryAddressId(deliveryAddressId);
			this.setBillingAddressId(billingAddressId);
			this.setBankCardUsedId(bankCardUsedId);
			this.setStatus(status);
			this.setIsDeleted(isDeleted);
			this.setUserId(userId);

		}

		public Order() {

			this(DEFAULT_ID, DEFAULT_ID, NULL_DATE, NULL_DATE, NULL_DATE, DEFAULT_DELIVERY, DEFAULT_ID, DEFAULT_ID,
					DEFAULT_ID, DEFAULT_STATUS,DEFAULT_IS_DELETE, DEFAULT_ID);

		}
		
		
		public Order(ResultSet resultSet) throws Exception {
			
			this (resultSet.getInt("id"), 
					resultSet.getInt("number"),
					Utils.toJavaDate(resultSet.getDate("create_date")),
					Utils.toJavaDate(resultSet.getDate("delivery_date")),
					Utils.toJavaDate(resultSet.getDate("cancellation_date")),
					DeliveryMethod.fromString(resultSet.getString("delivery_methode")),
					resultSet.getInt("delivery_address_id"),
					resultSet.getInt("billing_address_id"),
					resultSet.getInt("bankcard_id"),
					OrderStatus.fromString(resultSet.getString("status")),
					resultSet.getBoolean("is_deleted"),
					resultSet.getInt("user_id")
					);

			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Date getDeliveryDate() {
			return deliveryDate;
		}

		public void setDeliveryDate(Date deliveryDate) {
			this.deliveryDate = deliveryDate;
		}

		public Date getCancellationDate() {
			return cancellationDate;
		}

		public void setCancellationDate(Date cancellationDate) {
			this.cancellationDate = cancellationDate;
		}

		public DeliveryMethod getDeliveryMethod() {
			return deliveryMethod;
		}

		public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
			this.deliveryMethod = deliveryMethod;
		}

		public OrderStatus getStatus() {
			return status;
		}

		public void setStatus(OrderStatus status) {
			this.status = status;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getDeliveryAddressId() {
			return deliveryAddressId;
		}

		public void setDeliveryAddressId(int deliveryAddressId) {
			this.deliveryAddressId = deliveryAddressId;
		}

		public int getBillingAddressId() {
			return billingAddressId;
		}

		public void setBillingAddressId(int billingAddressId) {
			this.billingAddressId = billingAddressId;
		}

		public int getBankCardUsedId() {
			return bankCardUsedId;
		}

		public void setBankCardUsedId(int bankCardUsedId) {
			this.bankCardUsedId = bankCardUsedId;
		}

		public boolean getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		@Override
		public String toString() {
			final IBankCardDao myBankCardDao = new BankCardDao();
			final IAddressDao myAddressDao = new AddressDao();
			String stringReturn = "";
//			Address deliveryAdress = new Address();
//
//			Address billingAdress = new Address();
//			BankCard bankCard = new BankCard();

			try {
				stringReturn += String.format("id:%s order-number :%010d, create %s, ", this.getId(), this.getNumber(),
						Utils.date2String(this.getCreateDate(), "dd/MM/yyyy"));

				stringReturn += String.format("%s", this.getDeliveryDate() == null ? ""
						: "delivery : " + Utils.date2String(this.getDeliveryDate(), "dd/MM/yyyy"));

				stringReturn += String.format("%s", this.getCancellationDate() == null ? ""
						: " Cancel : " + Utils.date2String(this.getCancellationDate(), "dd/MM/yyyy"));

				stringReturn += String.format(" by %s  {%s} isDeleted : %b\n", 
						this.getDeliveryMethod().getValue(), this.getStatus(),this.getIsDeleted());

				stringReturn += String.format(" \t\t delivery address %s\n",
						myAddressDao.getById(this.getDeliveryAddressId()).toString());
				stringReturn += String.format(" \t\t billing address %s\n",
						myAddressDao.getById(this.getBillingAddressId()).toString());
				stringReturn += String.format(" \t\t Bank card %s\n",
						myBankCardDao.get(this.getBankCardUsedId()).toString());

			} catch (Exception e) {
				e.printStackTrace();
			}
			return stringReturn;

		}
}
