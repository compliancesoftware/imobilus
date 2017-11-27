package br.com.imobilus.admin.service.impl;

import java.util.Calendar;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobilus.admin.dao.UserDao;
import br.com.imobilus.admin.model.User;
import br.com.imobilus.admin.service.UserService;
import br.com.imobilus.admin.util.Logs;

@Service
@Transactional
public class UserServiceImpl extends GenericService<User> implements UserService{

	@Autowired
	UserDao userDao;

	@Override
	public User create(User user) throws Exception {
		User createdUser = null;
		
		user.setId(null);
		Calendar now = Calendar.getInstance();
		user.setCreatedAt(now);
		user.setUpdatedAt(now);
		user.setLastAccess(now);
		
		User collapse = getUserByName(user);
		if(collapse != null) {
			Logs.info("[UserServiceImpl] :: create :: Error trying to create new user: There's a user already set with the same name in database.");
			throw new Exception("Já existe usuário com este nome.");
		}
		else {
			collapse = getUserByPhone(user);
			if(collapse != null) {
				Logs.info("[UserServiceImpl] :: create :: Error trying to create new user: There's a user already set with the same phone number in database.");
				throw new Exception("Já existe usuário com este telefone: " + collapse.getPhone1());
			}
			else {
				collapse = getUserByEmail(user);
				if(collapse != null) {
					Logs.info("[UserServiceImpl] :: create :: Error trying to create new user: There's a user already set with the same e-mail in database.");
					throw new Exception("Já existe usuário com este email: " + collapse.getEmail());
				}
				else {
					createdUser = userDao.create(user);
					Logs.info("[UserServiceImpl] :: create :: Success!");
				}
			}
		}
		
		return createdUser;
	}

	@Override
	public User update(User user) throws Exception {
		User updatedUser = null;
		
		Calendar now = Calendar.getInstance();
		user.setUpdatedAt(now);
		
		User collapse = getUserByName(user);
		if(collapse != null && !(user.getId().equals(collapse.getId()))) {
			Logs.info("[UserServiceImpl] :: update :: Error trying to update user: There's a user already set with the same name in database.");
			throw new Exception("Já existe usuário com este nome.");
		}
		else {
			collapse = getUserByPhone(user);
			if(collapse != null && !(user.getId().equals(collapse.getId()))) {
				Logs.info("[UserServiceImpl] :: update :: Error trying to update user: There's a user already set with the same phone number in database.");
				throw new Exception("Já existe usuário com este telefone: " + collapse.getPhone1());
			}
			else {
				collapse = getUserByEmail(user);
				if(collapse != null && !(user.getId().equals(collapse.getId()))) {
					Logs.info("[UserServiceImpl] :: update :: Error trying to update user: There's a user already set with the same e-mail in database.");
					throw new Exception("Já existe usuário com este email: " + collapse.getEmail());
				}
				else {
					updatedUser = userDao.update(user);
					Logs.info("[UserServiceImpl] :: update :: Success!");
				}
			}
		}
		
		return updatedUser;
	}
	
	@Override
	public User getByEmail(String email) throws Exception {
		User user = new User();
		user.setEmail(email);
		User found = getUserByEmail(user);
		if(found == null) {
			Logs.info("[UserServiceImpl] :: getByEmail :: Error trying to retrieve user by email.");
			throw new Exception("Usuário não encontrado.");
		}
		else {
			Logs.info("[UserServiceImpl] :: getByEmail :: User found by e-mail: " + found);
		}
		return found;
	}
	
	private User getUserByName(User user) {
		try {
			User collapse = userDao.getByName(user.getName());
			return collapse;
		} catch(NoResultException e) {
			Logs.info("[UserServiceImpl] :: getUserByName :: Error trying to retrieve user by name: No result found.");
			return null;
		} catch(Exception e) {
			Logs.info("[UserServiceImpl] :: getUserByName :: Error trying to retrieve user by name: "+user.getName());
			e.printStackTrace();
			return null;
		}
	}
	
	private User getUserByPhone(User user) {
		try {
			User collapse = userDao.getByPhone1(user.getPhone1());
			if(collapse == null)
				collapse = userDao.getByPhone2(user.getPhone1());
			if(collapse == null)
				collapse = userDao.getByPhone1(user.getPhone2());
			if(collapse == null)
				collapse = userDao.getByPhone2(user.getPhone2());
			
			if(collapse != null) {
				String number = collapse.getPhone1() + " ou " + collapse.getPhone2();
				collapse.setPhone1(number);
			}
			
			return collapse;
		} catch(NoResultException e) {
			Logs.info("[UserServiceImpl] :: getUserByPhone :: Error trying to retrieve user by phone1: No result found.");
			return null;
		} catch(Exception e) {
			Logs.info("[UserServiceImpl] :: getUserByPhone :: Error trying to retrieve user by phone: ");
			e.printStackTrace();
			return null;
		}
	}
	
	private User getUserByEmail(User user) {
		try {
			User collapse = userDao.getByEmail(user.getEmail());
			return collapse;
		} catch(NoResultException e) {
			Logs.info("[UserServiceImpl] :: getUserByEmail :: Error trying to retrieve user by name: No result found.");
			return null;
		} catch(Exception e) {
			Logs.info("[UserServiceImpl] :: getUserByEmail :: Error trying to retrieve user by name: "+user.getEmail());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User authenticate(User user) throws Exception {
		User found = null;
		
		try {
			found = userDao.authenticate(user);
		} catch(NoResultException e) {
			Logs.info("[UserServiceImpl] :: authenticate :: Error trying to authenticate user: No result found.");
			found = null;
			throw new Exception("Falha de autenticação: Usuário ou senha incorretos.");
		} catch(Exception e) {
			Logs.info("[UserServiceImpl] :: authenticate :: Error trying to authenticate user: ");
			e.printStackTrace();
			found = null;
			throw new Exception("Falha de autenticação: Erro de servidor.");
		}
		
		return found;
	}

}
