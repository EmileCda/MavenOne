package fr.emile.mavenone;

import fr.emile.mavenone.entity.Address;
import fr.emile.mavenone.model.dao.AddressDao;
import fr.emile.mavenone.model.dao.IAddressDao;
import fr.emile.mavenone.utils.Utils;

public class MainAddressDelete {
	public MainAddressDelete() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final IAddressDao myAddressDao = new AddressDao();
		final int idAddress = 1;
		try {
			Address myAddress= myAddressDao.getById(idAddress);
			System.out.printf("%s to delete \n",myAddress);
			
			int nbimpacted = myAddressDao.delete(idAddress);
			System.out.printf("Delete succed : %d\n",nbimpacted);
			myAddress= myAddressDao.getById(idAddress);
			System.out.printf("%s to delete \n",myAddress);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error add %s", idAddress);
			
		}
	}
}
