package fr.emile.mavenone.model.dao;

import java.util.List;

import fr.emile.mavenone.entity.User;

public interface IUserDao {
	int add(User user) throws Exception;
	User get(int id) throws Exception;
	List<User> get() throws Exception;
	int Update(User user)throws Exception;
	int delete (int id)throws Exception;
	int delete (User user)throws Exception;
}
