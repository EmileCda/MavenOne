package fr.emile.mavenone;

import java.util.ArrayList;
import java.util.List;

import fr.emile.mavenone.entity.Order;
import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.dao.IOrderDao;
import fr.emile.mavenone.model.dao.OrderDao;

public class MainOrderDelete {

	public static void main(String[] args) {
		
//		int delete (Order order)throws Exception;
//		testByOrder () ;
	//		int delete (Order order,boolean cancel)throws Exception;
//		testByOrderUndo();
//		int delete(User user) throws Exception ;
//		testByUser();

//		int delete(User user,boolean cancel) throws Exception ;
		testByUserUndo ();
		System.out.println("************** end *************************\n");

	}
//------------------------------------------------------------------------------------------------
	public static void  testByOrderUndo () {
		final IOrderDao myOrderDao = new OrderDao();
		Order myOrder = new Order();
		int orderId = 31;
		System.out.println("------------ by order id r --------------------------------------\n");
		try {

			myOrder = myOrderDao.get(orderId);
			System.out.println(myOrder);
			int numberDelete = myOrderDao.delete(myOrder, false);
			System.out.printf("nb record affected : %d\n", numberDelete);
			myOrder = myOrderDao.get(orderId);
			System.out.println(myOrder);

			numberDelete = myOrderDao.delete(myOrder, true);
			System.out.printf("nb record affected recovered: %d\n", numberDelete);
			myOrder = myOrderDao.get(orderId);
			System.out.println(myOrder);

			numberDelete = myOrderDao.delete(myOrder, false);
			System.out.printf("nb record affected recovered: %d\n", numberDelete);
			myOrder = myOrderDao.get(orderId);
			System.out.println(myOrder);

			
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	//------------------------------------------------------------------------------------------------

	public static void  testByOrder () {
		final IOrderDao myOrderDao = new OrderDao();
		Order myOrder = new Order();
		int orderId = 31;

		System.out.println("------------ by order id r --------------------------------------\n");
		try {

			myOrder = myOrderDao.get(orderId);
			System.out.println(myOrder);
			int numberDelete = myOrderDao.delete(myOrder);
			System.out.printf("nb record affected : %d\n", numberDelete);
			myOrder = myOrderDao.get(orderId);
			System.out.println(myOrder);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//------------------------------------------------------------------------------------------------
		
	public static void  testByUserUndo () {
		final IOrderDao myOrderDao = new OrderDao();
		List<Order> myOrderList = new ArrayList<Order>();
				User user = new User();
		user.setId(6);

		try {

			myOrderList = myOrderDao.get(user);
			System.out.printf("**************** origin order ***************** \n" );
			for (Order order : myOrderList) {
				System.out.println(order );
			}
			int numberDelete = myOrderDao.delete(user,true);
			System.out.printf("nb record affected delete = true : %d\n", numberDelete);
			myOrderList = myOrderDao.get(user);
			for (Order order : myOrderList) {
				System.out.println(order );
			}
			numberDelete = myOrderDao.delete(user,false);
			System.out.printf("nb record affected delete false : %d\n", numberDelete);
			myOrderList = myOrderDao.get(user);
			for (Order order : myOrderList) {
				System.out.println(order );
			}
			numberDelete = myOrderDao.delete(user,true);
			System.out.printf("nb record affected delete true : %d\n", numberDelete);
			myOrderList = myOrderDao.get(user);
			for (Order order : myOrderList) {
				System.out.println(order );
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//------------------------------------------------------------------------------------------------
			
	public static void  testByUser () {
		final IOrderDao myOrderDao = new OrderDao();

		List<Order> myOrderList = new ArrayList<Order>();
		User user = new User();
		user.setId(5);
		System.out.println("------------ by user --------------------------------------\n");

		try {

			myOrderList = myOrderDao.get(user);
			System.out.println(myOrderList);
			int numberDelete = myOrderDao.delete(user);

			System.out.printf("nb record affected delete : %d\n", numberDelete);
			myOrderList = myOrderDao.get(user);
			for (Order order : myOrderList) {
				System.out.println(order );
				
			}


		
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
