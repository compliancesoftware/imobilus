package br.com.imobilus.admin.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.imobilus.admin.dao.PermissionDao;
import br.com.imobilus.admin.model.Permission;

@Repository
public class PermissionDaoImpl extends GenericDAO<Permission> implements PermissionDao{

	@Override
	public Permission getByDescription(String description) throws Exception {
		Query query = manager.createQuery("select p from Permission as p where p.description = :description");
		query.setParameter("description", description);
		
		Permission permission = (Permission) query.getSingleResult();
		return permission;
	}
}
