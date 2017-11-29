package br.com.imobilus.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobilus.admin.model.Permission;
import br.com.imobilus.admin.model.User;
import br.com.imobilus.admin.service.PermissionService;
import br.com.imobilus.admin.service.UserService;
import br.com.imobilus.admin.util.Logs;
import br.com.imobilus.admin.util.enums.Rules;
import br.com.imobilus.admin.util.response.ProcResponse;
import br.com.imobilus.admin.util.response.ProcResponse.Status;

@RestController
@RequestMapping("permissions")
public class PermissionController {
	
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "retrieve")
	public ResponseEntity<List<Permission>> retrivePermissions(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword) {
		List<Permission> permissions = new ArrayList<Permission>();
		ResponseEntity<List<Permission>> httpResponse = new ResponseEntity<List<Permission>>(permissions, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User user = new User();
			user.setName(userName);
			user.setPassword(userPassword);
			
			Logs.info("[PermissionController] :: retrivePermissions :: Authenticating user: "+user.getName());
			User authenticatedUser = userService.authenticate(user);
			
			if(authenticatedUser.getPermission().getRules().contains(""+Rules.ADMINISTRADOR)) {
				Logs.info("[PermissionController] :: retrivePermissions :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[PermissionController] :: retrivePermissions :: Retriving permissions...");
				
				permissions = permissionService.retrieve();
				
				if(permissions != null && permissions.size() > 0) {
					Logs.info("[PermissionController] :: retrivePermissions :: Retrived "+permissions.size()+" permissions.");
					httpResponse = new ResponseEntity<List<Permission>>(permissions, HttpStatus.OK);
				}
				else {
					Logs.info("[PermissionController] :: retrivePermissions :: No permissions to retrieve.");
					httpResponse = new ResponseEntity<List<Permission>>(permissions, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				Logs.info("[PermissionController] :: retrivePermissions :: Error trying to retrieve permissions: User isn't an administrator.");
				httpResponse = new ResponseEntity<List<Permission>>(permissions, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch(Exception e) {
			Logs.info("[PermissionController] :: retrivePermissions :: Error trying to retrieve permissions: ");
			e.printStackTrace();
			httpResponse = new ResponseEntity<List<Permission>>(permissions, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "create", consumes="application/json")
	public ResponseEntity<ProcResponse> createPermission(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Permission permission) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User user = new User();
			user.setName(userName);
			user.setPassword(userPassword);
			
			Logs.info("[PermissionController] :: createPermission :: Authenticating user: "+user.getName());
			User authenticatedUser = userService.authenticate(user);
			
			if(authenticatedUser.getPermission().getRules().contains(""+Rules.ADMINISTRADOR)) {
				Logs.info("[PermissionController] :: createPermission :: User authenticate success: " + authenticatedUser.getName());
				
				Logs.info("[PermissionController] :: createPermission :: Creating permission: "+permission.getDescription());
				Permission createdPermission = permissionService.create(permission);
				Logs.info("[PermissionController] :: createPermission :: Created permission: "+createdPermission.getDescription());
				
				response.setMessage("Permissão criada com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(createdPermission);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[PermissionController] :: createPermission :: Error trying to create permission: this user isn't a system administrator.");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[PermissionController] :: createPermission :: Error trying to create permission:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "update", consumes="application/json")
	public ResponseEntity<ProcResponse> updatePermission(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Permission permission) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User user = new User();
			user.setName(userName);
			user.setPassword(userPassword);
			
			Logs.info("[PermissionController] :: updatePermission :: Authenticating user: "+user.getName());
			User authenticatedUser = userService.authenticate(user);
			
			if(authenticatedUser.getPermission().getRules().contains(""+Rules.ADMINISTRADOR)) {
				Logs.info("[PermissionController] :: updatePermission :: User authenticate success: " + authenticatedUser.getName());
				
				Logs.info("[PermissionController] :: updatePermission :: Updating permission: "+permission.getDescription());
				Permission updatedPermission = permissionService.update(permission);
				Logs.info("[PermissionController] :: updatePermission :: Updated permission: "+updatedPermission.getDescription());
				
				response.setMessage("Permissão atualizada com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(updatedPermission);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[PermissionController] :: updatePermission :: Error trying to update permission:");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[PermissionController] :: updatePermission :: Error trying to update permission:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "delete", consumes="application/json")
	public ResponseEntity<ProcResponse> deletePermission(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Permission permission) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User user = new User();
			user.setName(userName);
			user.setPassword(userPassword);
			
			Logs.info("[PermissionController] :: deletePermission :: Authenticating user: " + user.getName());
			User authenticatedUser = userService.authenticate(user);
			
			if(authenticatedUser.getPermission().getRules().contains(""+Rules.ADMINISTRADOR)) {
				Logs.info("[PermissionController] :: deletePermission :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[PermissionController] :: deletePermission :: Deleting permission: "+permission.getDescription());
				permissionService.delete(permission.getId());
				Logs.info("[PermissionController] :: deletePermission :: Permission deleted");
				
				response.setMessage("Permissão excluida com êxito.");
				response.setStatus(Status.OK);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[PermissionController] :: deletePermission :: Error trying to delete permission: ");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[PermissionController] :: deletePermission :: Error trying to delete permission: ");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
}
