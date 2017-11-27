package br.com.imobilus.admin.dao;

import br.com.imobilus.admin.model.User;

public interface UserDao extends DAO<User> {
	public User getByName(String name) throws Exception;
	public User getByPhone1(String phone1) throws Exception;
	public User getByPhone2(String phone2) throws Exception;
	public User getByEmail(String email) throws Exception;
	
	public User authenticate(User user) throws Exception;
}
