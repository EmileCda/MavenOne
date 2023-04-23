package fr.emile.mavenone;

import java.util.List;

import fr.emile.mavenone.entity.BankCard;
import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.dao.BankCardDao;
import fr.emile.mavenone.model.dao.IBankCardDao;
import fr.emile.mavenone.model.dao.IUserDao;
import fr.emile.mavenone.model.dao.UserDao;
import fr.emile.mavenone.utils.Utils;

public class MainBankCardCreate {
	public static void main(String[] args) {
		final IBankCardDao myBankCardDao = new BankCardDao();
		final IUserDao myUserDao = new UserDao();
		List<User> userList = null ;

		BankCard myBankCard = null; 
		try {
			userList = myUserDao.get();
			
			for (User oneUser : userList) {
				int maxCard = Utils.randInt(1, 5);
				for (int index = 0; index < maxCard ; index ++)
				{
					int bin = Utils.randInt(0, 99999999);
					int digit3 = Utils.randInt(0, 999);
					int digit8 = Utils.randInt(0, 99999999);
					String dateString = String.format("%02d/%02d",Utils.randInt(1, 12),Utils.randInt(2023, 2028));
					
					myBankCard = new BankCard(String.format("%08d%08d",bin,digit8),
							Utils.string2Date(dateString,"MM/yy"),String.format("%03d",digit3), oneUser.getId());
					int id = myBankCardDao.add(myBankCard);
					System.out.printf("id : %d, BC %s\n", id, myBankCard);
					
				}
			}
				Utils.trace(myBankCard.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error add %s", myBankCard);
			
		}
		System.out.printf("**** End ************\n");
	}
}
