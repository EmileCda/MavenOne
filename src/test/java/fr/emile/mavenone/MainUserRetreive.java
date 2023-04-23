package fr.emile.mavenone;

import java.util.List;

import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.dao.IUserDao;
import fr.emile.mavenone.model.dao.UserDao;

public class MainUserRetreive {
	public MainUserRetreive() {
	}

	public static void main(String[] args) {
		testbyUser() ;
//		testAllUser();
		
	}
	public static void testbyUser() {
		final IUserDao myUserDao = new UserDao();
		User myUser = null; 
		try {
			myUser = myUserDao.get(5);
			System.out.println("== retreive one user ==================================================");
			System.out.println(myUser);		
			
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public static void testAllUser() {
		final IUserDao myUserDao = new UserDao();
		try {
			
			List<User> userList = myUserDao.get();
			
System.out.println("== retreive all user ==================================================");
			if (userList != null) {
				for (User oneUser : userList) {
					System.out.println(oneUser);
				}
			}else
				System.out.println("userList est vide\n");
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
