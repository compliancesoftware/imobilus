package br.com.imobilus.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobilus.admin.dao.DAO;
import br.com.imobilus.admin.model.Entity;
import br.com.imobilus.admin.service.Service;

@Component
@Transactional
public abstract class GenericService<T extends Entity> implements Service<T> {

	@Autowired
	private DAO<T> dao;
	
	@Override
	public T create(T t) throws Exception {
		return dao.create(t);
	}

	@Override
	public T update(T t) throws Exception {
		return dao.update(t);
	}

	@Override
	public void delete(Long id) throws Exception {
		dao.delete(id);
	}

	@Override
	public List<T> retrieve() throws Exception {
		return dao.retrieve();
	}

	@Override
	public T getById(Long id) throws Exception {
		return dao.getById(id);
	}

}
