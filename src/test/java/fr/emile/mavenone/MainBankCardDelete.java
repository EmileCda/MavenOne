package fr.emile.mavenone;

import java.util.ArrayList;
import java.util.List;

import fr.emile.mavenone.entity.BankCard;
import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.dao.BankCardDao;
import fr.emile.mavenone.model.dao.IBankCardDao;
import fr.emile.mavenone.model.dao.IUserDao;
import fr.emile.mavenone.model.dao.UserDao;
import fr.emile.mavenone.utils.Utils;

public class MainBankCardDelete {

	public static void main(String[] args) {
//		int delete (BankCard bankCard)throws Exception;
//		deleteByCard() ;

		// int delete(User user, boolean undo) throws Exception ;
//		deleteByCardUndo();

//		int delete(User user) throws Exception ;
//		deleteByUser();

		//		int delete(User user) throws Exception ;
		deleteByUserUndo();

	}

	public static void deleteByCard() {
//		int delete (BankCard bankCard)throws Exception;
		final IBankCardDao myBankCardDao = new BankCardDao();
		final int bankCardId = 2;
		BankCard myBankCard = null;
		try {

			myBankCard = new BankCard();
			myBankCard = myBankCardDao.get(bankCardId);
			Utils.trace("delete : " + myBankCard.toString());

			int nbCard = myBankCardDao.delete(myBankCard);
			Utils.trace("delete : " + nbCard);
			myBankCard = myBankCardDao.get(bankCardId);
			Utils.trace("retrieve : " + myBankCard.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error add %s", myBankCard);

		}
	}

	public static void deleteByCardUndo() {
//		int delete(User user, boolean undo) throws Exception ;
		final IBankCardDao myBankCardDao = new BankCardDao();
		final int bankCardId = 2;
		BankCard myBankCard = null;
		try {

			myBankCard = new BankCard();
			myBankCard = myBankCardDao.get(bankCardId);
			Utils.trace("delete : " + myBankCard.toString());

			int nbCard = myBankCardDao.delete(myBankCard, false);
			Utils.trace("delete = false: " + nbCard);
			myBankCard = myBankCardDao.get(bankCardId);
			Utils.trace("retrieve : " + myBankCard.toString());

			nbCard = myBankCardDao.delete(myBankCard, true);
			Utils.trace("delete :true " + nbCard);
			myBankCard = myBankCardDao.get(bankCardId);
			Utils.trace("retrieve : " + myBankCard.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error add %s", myBankCard);

		}

	}

	public static void deleteByUser() {
//		int delete(User user) throws Exception ;
		final IBankCardDao myBankCardDao = new BankCardDao();
		final IUserDao myUserDao = new UserDao();
		final int userId = 12;
		BankCard myBankCard = null;
		try {
			User user = new User();

			user = myUserDao.get(userId);
			int nbCard = myBankCardDao.delete(user);
			System.out.printf("User: %s nbCard :%d", user, nbCard);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error add %s", myBankCard);

		}

	}

	public static void deleteByUserUndo() {
//		int delete(User user, boolean undo) throws Exception ;
		List<BankCard> myBankCardList = new ArrayList<BankCard>();

		final IBankCardDao myBankCardDao = new BankCardDao();
		final IUserDao myUserDao = new UserDao();
		final int userId = 12;
		BankCard myBankCard = null;
		try {
			User user = new User();

			user = myUserDao.get(userId);
			int nbCard = myBankCardDao.delete(user, false);
			System.out.printf(" %s %s nbCard :%d  undo = false\n", user.getFirstname(),user.getLastname(), nbCard);
			myBankCardList = myBankCardDao.get(user);
			for (BankCard bankCard : myBankCardList) {
				System.out.printf(" %s\n", bankCard);
			}

			nbCard = myBankCardDao.delete(user, true);
			System.out.printf(" %s %s nbCard :%d  undo = false\n", user.getFirstname(),user.getLastname(), nbCard);
			myBankCardList = myBankCardDao.get(user);
			for (BankCard bankCard : myBankCardList) {
				System.out.printf(" %s\n", bankCard);
			}

			nbCard = myBankCardDao.delete(user, false);
			System.out.printf(" %s %s nbCard :%d  undo = false\n", user.getFirstname(),user.getLastname(), nbCard);
			myBankCardList = myBankCardDao.get(user);
			for (BankCard bankCard : myBankCardList) {
				System.out.printf(" %s\n", bankCard);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error add %s\n", myBankCard);

		}

	}

}
