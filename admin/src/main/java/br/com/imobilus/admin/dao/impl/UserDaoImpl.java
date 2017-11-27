package br.com.imobilus.admin.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.imobilus.admin.dao.UserDao;
import br.com.imobilus.admin.model.User;
import br.com.imobilus.admin.util.Encoder;

@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao{

	@Override
	public User getByName(String name) throws Exception {
		Query query = manager.createQuery("select u from User as u where u.name = :name");
		query.setParameter("name", name);
		User user = (User) query.getSingleResult();
		return user;
	}

	@Override
	public User getByPhone1(String phone1) throws Exception {
		Query query = manager.createQuery("select u from User as u where u.phone1 = :phone1");
		query.setParameter("phone1", phone1);
		User user = (User) query.getSingleResult();
		return user;
	}

	@Override
	public User getByPhone2(String phone2) throws Exception {
		Query query = manager.createQuery("select u from User as u where u.phone2 = :phone2");
		query.setParameter("phone2", phone2);
		User user = (User) query.getSingleResult();
		return user;
	}

	@Override
	public User getByEmail(String email) throws Exception {
		Query query = manager.createQuery("select u from User as u where u.email = :email");
		query.setParameter("email", email);
		User user = (User) query.getSingleResult();
		return user;
	}

	@Override
	public User authenticate(User user) throws Exception {
		Query query = manager.createQuery("select u from User as u where u.name = :name");
		query.setParameter("name", user.getName());
		User found = (User) query.getSingleResult();
		
		if(!Encoder.encode(found.getPassword()).equals(user.getPassword()))
			throw new NoResultException("Falha de autenticação: Usuário ou senha incorretos.");
		
		return found;
	}
}
