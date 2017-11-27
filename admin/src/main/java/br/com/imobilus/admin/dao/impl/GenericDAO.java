package br.com.imobilus.admin.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.core.GenericTypeResolver;

import br.com.imobilus.admin.dao.DAO;
import br.com.imobilus.admin.model.Entity;

public abstract class GenericDAO<T extends Entity> implements DAO<T> {
	
	@PersistenceContext
	protected EntityManager manager;
	
	private Class<T> genericType;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDAO.class);
	}
	
	@Override
	public T create(T t) {
		manager.persist(t);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> retrieve() {
		Query query = manager.createQuery("SELECT p FROM " + genericType.getName() + " as p");
		return query.getResultList();
	}
	
	@Override
	public void delete(Long id) {
		T object = (T) manager.find(genericType, id);
		manager.remove(object);
	}
	
	@Override
	public T update(T t) {
		manager.merge(t);
		return t;
	}
	
	@Override
	public T getById(Long id) {
		T object = (T) manager.find(genericType, id);
		return object;
	}
}
