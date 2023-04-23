package fr.emile.mavenone;

import java.util.ArrayList;
import java.util.List;

import fr.emile.mavenone.entity.Order;
import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.dao.IOrderDao;
import fr.emile.mavenone.model.dao.OrderDao;

public class MainOrderRetreive {

	public static void main(String[] args) {
//		Order get(int id) throws Exception;
//		testOneOrder();

//		List<Order> get(User user) throws Exception;
//		testOrderByUser();
		
		
//		List<Order> get() throws Exception;
		testAllOrder();


	}

//-------------------------------------------------------------------------------------------------
	public static void testOneOrder() {
		final IOrderDao myOrderDao = new OrderDao();
		Order myOrder = new Order();
		try {
			myOrder = myOrderDao.get(31);
			System.out.println(myOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//-------------------------------------------------------------------------------------------------
	public static void testOrderByUser() {
		final IOrderDao myOrderDao = new OrderDao();

		List<Order> orderList = new ArrayList<Order>();
		User myUser = new User();
		myUser.setId(6);
		try {
			orderList = myOrderDao.get(myUser);
			for (Order order : orderList) {
				System.out.println(order);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------------------------------------------
	public static void testAllOrder() {
		final IOrderDao myOrderDao = new OrderDao();
		List<Order> orderList = new ArrayList<Order>();
		try {
			orderList = myOrderDao.get();
			for (Order order : orderList) {
				System.out.println(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
