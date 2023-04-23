package fr.emile.mavenone.model.dao;

import java.util.List;

import fr.emile.mavenone.entity.BankCard;
import fr.emile.mavenone.entity.User;

public interface IBankCardDao {
	int add(BankCard bankCard) throws Exception; 
	BankCard get(int id) throws Exception;
	List<BankCard> get(User user) throws Exception;
	List<BankCard> get() throws Exception;
	int Invalidate(BankCard bankCard) throws Exception;
	int Invalidate(BankCard bankCard, boolean undo) throws Exception;
	int delete (BankCard bankCard, boolean undo)throws Exception;
	int delete (BankCard bankCard)throws Exception;
	int delete(User user, boolean undo) throws Exception ;
	int delete(User user) throws Exception ;
}
