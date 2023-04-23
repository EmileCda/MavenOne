package fr.emile.mavenone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.emile.mavenone.common.IConstant;
import fr.emile.mavenone.entity.Address;
import fr.emile.mavenone.entity.BankCard;
import fr.emile.mavenone.entity.Order;
import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.enums.DeliveryMethod;
import fr.emile.mavenone.enums.OrderStatus;
import fr.emile.mavenone.model.dao.IOrderDao;
import fr.emile.mavenone.model.dao.IUserDao;
import fr.emile.mavenone.model.dao.OrderDao;
import fr.emile.mavenone.model.dao.UserDao;
import fr.emile.mavenone.utils.Utils;

public class MainOrderCreate implements IConstant {

	public static void main(String[] args) {
//		int add(Order order) throws Exception; 
//		simpleAdd() ;

		multipleAdd();

		System.out.println("******************* end ****************************");
	}
//-------------------------------------------------------------------------------------------------
	
	public static void multipleAdd() {

		final IOrderDao myOrderDao = new OrderDao();
		final IUserDao myUserDao = new UserDao();
		List<User> userList = null;

		Order myOrder = null;

		try {
			userList = myUserDao.get();

			for (User oneUser : userList) {
				Utils.trace(oneUser.toString());
				List<Address> addressList = new ArrayList<Address>();
				addressList = oneUser.getAddressList();

				List<BankCard> bankCardList = new ArrayList<BankCard>();
				bankCardList = oneUser.getBankCardList();

				int maxOrder = Utils.randInt(0, 3);

				for (int index = 0; index < maxOrder; index++) {
					Calendar today1 = Calendar.getInstance();
					Calendar today2 = Calendar.getInstance();
					Calendar today3 = Calendar.getInstance();
					today1.set(Calendar.HOUR_OF_DAY, 0);
					int randJour = Utils.randInt(0, 999);

					today1.add(Calendar.DATE, (-randJour));
					Date createDate = today1.getTime();

					today2.add(Calendar.DATE, -randJour + Utils.randInt(0, 9));
					today3.add(Calendar.DATE, -randJour + Utils.randInt(0, 9));
					Date deliveryDate = today2.getTime();
					Date cancellationDate = today3.getTime();
					OrderStatus status = OrderStatus.IN_PROGRESS;
					if (deliveryDate.compareTo(cancellationDate) >= 0) {
						cancellationDate = null;
						status = OrderStatus.VALIDATED;
					} else {
						deliveryDate = null;
						status = OrderStatus.CANCELLED;

					}
					Utils.trace(String.format(" D: %s C:%s %s", deliveryDate, cancellationDate, status.getValue()));

					int deliveryAddressId = addressList.get(Utils.randInt(0, addressList.size() - 1)).getId();
					int billingAddressId = addressList.get(Utils.randInt(0, addressList.size() - 1)).getId();
					int bankCardUsedId = bankCardList.get(Utils.randInt(0, bankCardList.size() - 1)).getId();

					myOrder = new Order(DEFAULT_ID, Utils.randInt(1, 9999), createDate, deliveryDate, cancellationDate,
							DeliveryMethod.values()[Utils.randInt(0, 3)], deliveryAddressId, billingAddressId,
							bankCardUsedId, status, false, oneUser.getId());

					Utils.trace(myOrder.toString());
					myOrderDao.add(myOrder);
				}
			}
			Utils.trace(myOrder.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error add %s", myOrder);

		}

	}

//-------------------------------------------------------------------------------------------------
	public static void simpleAdd() {
//		int add(Order order) throws Exception; 
		final IOrderDao myOrderDao = new OrderDao();
		Order myOrder;
		try {
			myOrder = new Order(DEFAULT_ID, 0, 
					Utils.string2Date("14/07/1884", "dd/MM/yyyy"),
					Utils.string2Date("15/08/1900", "dd/MM/yyyy"), 
					Utils.string2Date("11/11/1911", "dd/MM/yyyy"),
					DeliveryMethod.CHRONOPOST, 2, 3, 1, OrderStatus.IN_PROGRESS, false, 5);
			myOrderDao.add(myOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
