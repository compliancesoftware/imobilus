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

import br.com.imobilus.admin.model.User;
import br.com.imobilus.admin.service.UserService;
import br.com.imobilus.admin.util.Logs;
import br.com.imobilus.admin.util.enums.Rules;
import br.com.imobilus.admin.util.request.UserRequest;
import br.com.imobilus.admin.util.response.ProcResponse;
import br.com.imobilus.admin.util.response.ProcResponse.Status;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("retrieve")
	public ResponseEntity<List<User>> retriveUsers(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword) {
		List<User> users = new ArrayList<User>();
		ResponseEntity<List<User>> httpResponse = new ResponseEntity<List<User>>(users, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[UserController] :: retriveUsers :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			if(authenticatedUser.getPermission().getRules().contains(""+Rules.ADMINISTRADOR)) {
				Logs.info("[UserController] :: retriveUsers :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[UserController] :: retriveUsers :: Retriving users...");
				users = userService.retrieve();
				
				if(users != null && users.size() > 0) {
					Logs.info("[UserController] :: retriveUsers :: Retrived "+users.size()+" users.");
					
					httpResponse = new ResponseEntity<List<User>>(users, HttpStatus.OK);
				}
				else {
					Logs.info("[UserController] :: retriveUsers :: No users to retrieve.");
					httpResponse = new ResponseEntity<List<User>>(users, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				Logs.info("[UserController] :: retriveUsers :: Error trying to retrieve users: User ins't a system administrator.");
				users.add(authenticatedUser);
				httpResponse = new ResponseEntity<List<User>>(users, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[UserController] :: retriveUsers :: Error trying to retrieve users: ");
			e.printStackTrace();
			httpResponse = new ResponseEntity<List<User>>(users, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "create", consumes="application/json")
	public ResponseEntity<ProcResponse> createUser(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody UserRequest userRequest) {
		User user = userRequest.toUser();
		
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[UserController] :: createUser :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(authenticatedUser.getPermission().getRules().contains(""+Rules.ADMINISTRADOR)) {
				Logs.info("[UserController] :: createUser :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[UserController] :: createUser :: Creating user: "+user);
				User createdUser = userService.create(user);
				Logs.info("[UserController] :: createUser :: User created. ");
				
				response.setMessage("Usuário criado com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(createdUser);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[UserController] :: createUser :: Error trying to create user: User on autheticating isn't a system administrator.");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[UserController] :: createUser :: Error trying to create user:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "update", consumes="application/json")
	public ResponseEntity<ProcResponse> updateUser(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody UserRequest userRequest) {
		User user = userRequest.toUser();
		
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[UserController] :: updateUser :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(authenticatedUser.getPermission().getRules().contains(""+Rules.ADMINISTRADOR)) {
				Logs.info("[UserController] :: updateUser :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[UserController] :: updateUser :: Updating user: "+user);
				User updatedUser = userService.update(user);
				Logs.info("[UserController] :: updateUser :: Updated user: "+updatedUser);
				
				response.setMessage("Usuário atualizado com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(updatedUser);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				if(authenticatedUser.getId().equals(user.getId())) {
					Logs.info("[UserController] :: updateUser :: User authenticate success: " + authenticatedUser);
					
					Logs.info("[UserController] :: updateUser :: Updating user: "+user);
					User updatedUser = userService.update(user);
					Logs.info("[UserController] :: updateUser :: Updated user: "+updatedUser);
					
					response.setMessage("Usuário atualizado com êxito!");
					response.setStatus(Status.OK);
					response.setEntity(updatedUser);
					httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
				}
				else {
					Logs.info("[UserController] :: updateUser :: Error trying to update user: User on autheticating isn't a system administrator.");
					
					response.setStatus(Status.ERROR);
					response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
					
					httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} catch(Exception e) {
			Logs.info("[UserController] :: updateUser :: Error trying to update user:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "authenticate", consumes="application/json")
	public ResponseEntity<ProcResponse> authenticateUser(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User user = new User();
			user.setName(userName);
			user.setPassword(userPassword);
			
			Logs.info("[UserController] :: authenticateUser :: Authenticating user: " + user);
			User authenticatedUser = userService.authenticate(user);
			Logs.info("[UserController] :: authenticateUser :: User authenticated.");
			
			response.setMessage("Usuário autenticado com êxito!");
			response.setStatus(Status.OK);
			response.setEntity(authenticatedUser);
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
		} catch(Exception e) {
			Logs.info("[UserController] :: authenticateUser :: Error trying to authenticate user:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "delete", consumes="application/json")
	public ResponseEntity<ProcResponse> deleteUser(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody User user) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[UserController] :: deleteUser :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(authenticatedUser.getPermission().getRules().contains(""+Rules.ADMINISTRADOR)) {
				Logs.info("[UserController] :: deleteUser :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[UserController] :: deleteUser :: Deleting user: "+user);
				userService.delete(user.getId());
				Logs.info("[UserController] :: deleteUser :: User deleted");
				
				response.setMessage("Usuário excluido com êxito.");
				response.setStatus(Status.OK);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[UserController] :: deleteUser :: Error trying to delete user: User on autheticating isn't a system administrator.");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[UserController] :: deleteUser :: Error trying to delete user: ");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
}
