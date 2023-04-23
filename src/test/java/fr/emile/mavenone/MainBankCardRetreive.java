package fr.emile.mavenone;

import java.util.ArrayList;
import java.util.List;

import fr.emile.mavenone.entity.BankCard;
import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.dao.BankCardDao;
import fr.emile.mavenone.model.dao.IBankCardDao;
import fr.emile.mavenone.utils.Utils;

public class MainBankCardRetreive {

	public static void main(String[] args) {
//		retreiveById();
//			BankCard get(int id) throws Exception;
//		retreiveByUser();
//			List<BankCard> get(User user) throws Exception;

		retreiveAll();
//			List<BankCard> get() throws Exception;			
System.out.println("************************ fin ********************");
	}

	public static void retreiveById() {
//			BankCard get(int id) throws Exception;
		final IBankCardDao myBankCardDao = new BankCardDao();
		final int bankCardId = 1 ;
		BankCard myBankCard = null; 
		try {
			
			myBankCard = new BankCard();
			myBankCard= myBankCardDao.get(bankCardId);
			Utils.trace(myBankCard.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error add %s", myBankCard);
			
		}
	}

	public static void retreiveByUser() {
//			List<BankCard> get(User user) throws Exception;
		final IBankCardDao myBankCardDao = new BankCardDao();
		final int bankCardId = 1 ;
		List<BankCard> myBankCardList= new ArrayList<BankCard>();
		User myUser = new User(); 
		
		try {
			myUser.setId(5);	
			myBankCardList= myBankCardDao.get(myUser);
			
			for (BankCard bankCard : myBankCardList) {
				
				System.out.printf(" %s\n", bankCard);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error Fget");
			
		}

	}

	public static void retreiveAll() {
//			List<BankCard> get() throws Exception;	
		final IBankCardDao myBankCardDao = new BankCardDao();
		List<BankCard> myBankCardList= new ArrayList<BankCard>();
		
		try {
			myBankCardList= myBankCardDao.get();
			
			for (BankCard bankCard : myBankCardList) {
				
				System.out.printf(" %s\n", bankCard);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error Fget");
			
		}
	}

}
