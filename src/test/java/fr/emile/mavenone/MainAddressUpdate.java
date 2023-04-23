package fr.emile.mavenone;

import java.util.List;

import fr.emile.mavenone.entity.Address;
import fr.emile.mavenone.model.dao.AddressDao;
import fr.emile.mavenone.model.dao.IAddressDao;

public class MainAddressUpdate {
	public MainAddressUpdate() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		final IAddressDao myAddressDao = new AddressDao();
		List<Address>  addressList = null;   
		final int userId = 10 ;
		try {
			addressList = myAddressDao.get(userId);
			Address firstAddress = addressList.get(0);
			System.out.println(firstAddress);
			firstAddress.setCity("Quincy-voisins");
			myAddressDao.Update(firstAddress);
			addressList = myAddressDao.get(userId);
			firstAddress = addressList.get(0);
			System.out.println(firstAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
