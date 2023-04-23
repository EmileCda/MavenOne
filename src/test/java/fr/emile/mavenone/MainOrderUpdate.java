package fr.emile.mavenone;

import fr.emile.mavenone.entity.Order;
import fr.emile.mavenone.enums.OrderStatus;
import fr.emile.mavenone.model.dao.IOrderDao;
import fr.emile.mavenone.model.dao.OrderDao;

public class MainOrderUpdate {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final IOrderDao myOrderDao = new OrderDao();
		Order myOrder = new Order();
		int orderId = 31;
		try {

			myOrder = myOrderDao.get(orderId);
			System.out.println(myOrder);
			OrderStatus myOrderStatus = OrderStatus.IN_PROGRESS;
			myOrder.setStatus(myOrderStatus);
			int status = myOrderDao.Update(myOrder);
			System.out.printf("modif order status [%s] : nb impact : %d\n",
					myOrderStatus.getValue(),status);
			myOrder = myOrderDao.get(orderId);
			System.out.println(myOrder);
			
			int number = 1234 ; 
			myOrder.setNumber(number);
			status = myOrderDao.Update(myOrder);
			System.out.printf("modif order number [%d] : nb impact : %d\n",
					number,status);
			myOrder = myOrderDao.get(orderId);
			System.out.println(myOrder);
			
			

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
