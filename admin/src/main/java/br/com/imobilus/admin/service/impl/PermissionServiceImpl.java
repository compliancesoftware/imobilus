package br.com.imobilus.admin.service.impl;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobilus.admin.dao.PermissionDao;
import br.com.imobilus.admin.model.Permission;
import br.com.imobilus.admin.service.PermissionService;
import br.com.imobilus.admin.util.Logs;

@Service
@Transactional
public class PermissionServiceImpl extends GenericService<Permission> implements PermissionService{

	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public Permission create(Permission permission) throws Exception {
		Permission createdPermission = null;
		
		permission.setId(null);
		
		Permission collapse = getPermissionByDescription(permission);
		if(collapse != null) {
			Logs.info("[PermissionServiceImpl] :: create :: Error trying to create new permission: There is a permission with the same description already persisted.");
			throw new Exception("Já existe permissão com este nome.");
		}
		else {
			createdPermission = permissionDao.create(permission);
			Logs.info("[PermissionServiceImpl] :: create :: Success!");
		}
		
		return createdPermission;
	}

	@Override
	public Permission update(Permission permission) throws Exception {
		Permission updatedPermission = null;
		
		Permission collapse = getPermissionByDescription(permission);
		if((collapse != null) && !(collapse.getId().equals(permission.getId()))) {
			Logs.info("[PermissionServiceImpl] :: update :: Error trying to update permission: There is a permission with the same description already persisted.");
			throw new Exception("Já existe permissão com este nome.");
		}
		else {
			updatedPermission = permissionDao.update(permission);
			Logs.info("[PermissionServiceImpl] :: update :: Success!");
		}
		
		return updatedPermission;
	}

	private Permission getPermissionByDescription(Permission permission) {
		try {
			Permission collapse = permissionDao.getByDescription(permission.getDescription());
			return collapse;
		} catch(NoResultException e) {
			Logs.info("[PermissionServiceImpl] :: getPermissionByDescription :: Error trying to retrieve permission by description: No result found.");
			return null;
		} catch(Exception e) {
			Logs.info("[PermissionServiceImpl] :: getPermissionByDescription :: Error trying to retrieve permission by description: "+permission.getDescription());
			e.printStackTrace();
			return null;
		}
	}
	
}
