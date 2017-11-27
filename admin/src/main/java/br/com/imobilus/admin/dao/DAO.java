package br.com.imobilus.admin.dao;

import java.util.List;

import br.com.imobilus.admin.model.Entity;

public interface DAO<T extends Entity> {
	public T create(T t) throws Exception;
	public T update(T t) throws Exception;
	public void delete(Long id) throws Exception;
	public List<T> retrieve() throws Exception;
	public T getById(Long id) throws Exception;
}
