package br.com.imobilus.admin.dao;

import br.com.imobilus.admin.model.Permission;

public interface PermissionDao extends DAO<Permission>{
	public Permission getByDescription(String description) throws Exception;
}
