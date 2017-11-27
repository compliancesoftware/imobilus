package br.com.imobilus.admin.service;

import br.com.imobilus.admin.model.User;

public interface UserService extends Service<User>{
	public User getByEmail(String email) throws Exception;
	public User authenticate(User user) throws Exception;
}
