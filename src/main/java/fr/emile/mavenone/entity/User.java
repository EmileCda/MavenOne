package fr.emile.mavenone.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.emile.mavenone.common.IConstant;
import fr.emile.mavenone.utils.Utils;



public class User implements IConstant {
	private int id ; 
	private String firstname ; 
	private String lastname ; 
	private Date dateOfBirth ; 
	private List<Address> addressList ;
	private List<BankCard> bankCardList ;
	private List<Order> orderList ;

//---------------------------------------------------------------------------------------------------------------------	
	//------------------------ constructor --------------
	/**
	 * @param firstname
	 * @param lastname
	 * @param dateOfBirth
	 */
	public User(int id, String firstname, String lastname, Date dateOfBirth) {
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname ( lastname);
		this.setDateOfBirth ( dateOfBirth);
		addressList = new ArrayList<Address>(); 
		bankCardList= new ArrayList<BankCard>();
		orderList = new ArrayList<Order>();

	}
	public User() {
		this(DEFAULT_ID,DEFAULT_FIRSTNAME, DEFAULT_LASTTNAME,new Date());
	}
	
	public User(String firstname, String lastname, Date dateOfBirth) {
		this(DEFAULT_ID,firstname, lastname,dateOfBirth);
	}

//---------------------------------------------------------------------------------------------------------------------	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<Address> getAddressList() {
		return addressList;
	}
	public void addAddress(Address address) {
		this.getAddressList().add(address);
	}
	public void addAddress(List<Address> addressList) {
		
		for (Address address : addressList) {
			this.addAddress( address);
		}
	}

	public List<BankCard> getBankCardList() {
		return bankCardList;
	}
	
	public void addBankCard(List<BankCard> bankCardList) {
		for (BankCard bankCard : bankCardList) {
			this.addBankCard( bankCard) ;
		}
	}
	public void addBankCard(BankCard bankCard) {
		
		if (!bankCard.getIsDeleted())		// add only bankCard not deleted
			this.getBankCardList().add( bankCard);
	}

	public List<Order> getOrderList() {
		return orderList;
	}
	public void addOrder(List<Order> orderList) {
		for (Order order : orderList) {
			this.addOrder(order) ;
		}
	}
	public void addOrder(Order order) {
		this.getOrderList().add(order);
	}
//---------------------------------------------------------------------------------------------------------------------	
	@Override
	public String toString() {
		
		String result= String.format(
				"id %d=> %s %s ",
				getId(), getFirstname(), getLastname());

		try {
			result +=  String.format("birthday %s\n",Utils.date2String(this.getDateOfBirth(),"dd/MM/yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		result += "Adresse\n";
		for (Address address : addressList) {
			result += "\t"+ address.toString() + "\n";
		}

		result += "Carte bancaire\n";
		for (BankCard bankCard : this.getBankCardList()) {
			result += "\t"+ bankCard.toString() + "\n";
		}
		result += "Commande\n";
		for (Order order: this.getOrderList()) {
			result += "\t"+ order.toString() + "\n";
		}
		
		return result ;
	}
}
