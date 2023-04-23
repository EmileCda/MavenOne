package fr.emile.mavenone;

import java.util.List;

import fr.emile.mavenone.entity.Address;
import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.dao.AddressDao;
import fr.emile.mavenone.model.dao.IAddressDao;
import fr.emile.mavenone.model.dao.IUserDao;
import fr.emile.mavenone.model.dao.UserDao;

public class MainAddressRetreive {
	public MainAddressRetreive() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		
		//List<Address> get(int userId) throws Exception;
//		testGetAddressByUserId();  
		
		//List<Address> get(User user) throws Exception;
//		testGetAddressByUser();  
		
		//List<Address> get() throws Exception;
//		testGetAllAddress();
		
		//Address getById(int Id) throws Exception ;
		testGetAddressById() ;
		
	}

	public static void testGetAddressByUserId() {
		//List<Address> get(int userId) throws Exception;
		final IAddressDao myAddressDao = new AddressDao();
		final IUserDao myUserDao = new UserDao();
		List<Address>  addressList = null;
		User user = new User(); 
		try {

			user = myUserDao.get(6);
			addressList = myAddressDao.get(user.getId());
			
			if (addressList != null) {
				for (Address oneAddress : addressList) {
					System.out.println(oneAddress);
				}
			}else
				System.out.println("addressList est vide\n");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public static void testGetAddressByUser() {
		//List<Address> get(User user) throws Exception;
		final IAddressDao myAddressDao = new AddressDao();
		final IUserDao myUserDao = new UserDao();
		List<Address>  addressList = null;
		User user = new User(); 
		try {

			user = myUserDao.get(6);
			addressList = myAddressDao.get(user);
			
			if (addressList != null) {
				for (Address oneAddress : addressList) {
					System.out.println(oneAddress);
				}
			}else
				System.out.println("addressList est vide\n");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void testGetAllAddress() {
		//List<Address> get() throws Exception;
		final IAddressDao myAddressDao = new AddressDao();
		List<Address>  addressList = null;
		try {

			addressList = myAddressDao.get();
			
			if (addressList != null) {
				for (Address oneAddress : addressList) {
					System.out.println(oneAddress);
				}
			}else
				System.out.println("addressList est vide\n");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	public static void testGetAddressById() {
		//Address getById(int Id) throws Exception ;

		final IAddressDao myAddressDao = new AddressDao();
		Address  myAddress = new Address(); 
		try {

			myAddress = myAddressDao.getById(10);
			
			if (myAddress != null) {
				System.out.println(myAddress);
			}else
				System.out.println("addressList est vide\n");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}


