package br.com.imobilus.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobilus.admin.model.Client;
import br.com.imobilus.admin.model.User;
import br.com.imobilus.admin.service.ClientService;
import br.com.imobilus.admin.service.UserService;
import br.com.imobilus.admin.util.Logs;
import br.com.imobilus.admin.util.Rules;
import br.com.imobilus.admin.util.response.ProcResponse;
import br.com.imobilus.admin.util.response.ProcResponse.Status;

@RestController
@RequestMapping("clients")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "create", consumes="application/json")
	public ResponseEntity<ProcResponse> createClient(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Client client) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[ClientController] :: createClient :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(canModify(authenticatedUser)) {
				Logs.info("[ClientController] :: createClient :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[ClientController] :: createClient :: Creating client: "+client);
				client.setCreatedBy(authenticatedUser);
				Client createdClient = clientService.create(client);
				Logs.info("[ClientController] :: createClient :: Client created. ");
				
				response.setMessage("Cliente cadastrado com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(createdClient);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[ClientController] :: createClient :: Error trying to create client:");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[ClientController] :: createClient :: Error trying to create client:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "update", consumes="application/json")
	public ResponseEntity<ProcResponse> updateClient(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Client client) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[ClientController] :: updateClient :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(canModify(authenticatedUser)) {
				Logs.info("[ClientController] :: updateClient :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[ClientController] :: updateClient :: Updating client: "+client);
				client.setUpdatedBy(authenticatedUser);
				Client updatedClient = clientService.update(client);
				Logs.info("[ClientController] :: updateClient :: Client updated. ");
				
				response.setMessage("Cliente atualizado com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(updatedClient);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[ClientController] :: updateClient :: Error trying to update client:");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[ClientController] :: updateClient :: Error trying to update client:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "delete", consumes="application/json")
	public ResponseEntity<ProcResponse> deleteClient(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Client client) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[ClientController] :: deleteClient :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(canModify(authenticatedUser)) {
				Logs.info("[ClientController] :: deleteClient :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[ClientController] :: deleteClient :: Deleting client: "+client);
				clientService.delete(client.getId());
				Logs.info("[ClientController] :: deleteClient :: Client deleted");
				
				response.setMessage("Cliente removido com êxito.");
				response.setStatus(Status.OK);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[ClientController] :: deleteClient :: Error trying to delete client:");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[ClientController] :: deleteClient :: Error trying to delete client: ");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "retrieve", consumes="application/json")
	public ResponseEntity<List<Client>> retriveClients(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Client filter) {
		List<Client> clients = new ArrayList<Client>();
		ResponseEntity<List<Client>> httpResponse = new ResponseEntity<List<Client>>(clients, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[ClientController] :: retriveClients :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(canConsult(authenticatedUser)) {
				Logs.info("[ClientController] :: retriveClients :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[ClientController] :: retriveClients :: Retriving clients...");
				
				clients = clientService.listClientsWithFilter(filter);
				
				if(clients != null && clients.size() > 0) {
					Logs.info("[ClientController] :: retriveClients :: Retrived "+clients.size()+" clients.");
					httpResponse = new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
				}
				else {
					Logs.info("[ClientController] :: retriveClients :: No clients to retrieve.");
					httpResponse = new ResponseEntity<List<Client>>(clients, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				Logs.info("[ClientController] :: retriveClients :: No clients to retrieve: User authentication failed.");
				httpResponse = new ResponseEntity<List<Client>>(clients, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[ClientController] :: retriveClients :: Error trying to retrieve clients: ");
			e.printStackTrace();
			httpResponse = new ResponseEntity<List<Client>>(clients, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}

	private boolean canModify(User user) {
		boolean canModify = false;
		List<Rules> rules = user.getPermission().getRulesAsList();
		
		for(Rules rule : rules) {
			Logs.info("[ClientController] :: canModify :: " + user.getName() + " rule: "+rule.getValue());
			if(rule.equals(Rules.ADMINISTRADOR) || rule.equals(Rules.GERENTE)) {
				Logs.info("[ClientController] :: canModify :: true");
				canModify = true;
			}
		}
		
		return canModify;
	}
	
	private boolean canConsult(User user) {
		boolean canConsult = false;
		List<Rules> rules = user.getPermission().getRulesAsList();
		
		for(Rules rule : rules) {
			Logs.info("[ClientController] :: canConsult :: " + user.getName() + " rule: "+rule.getValue());
			if(!rule.equals(Rules.TESTE)) {
				Logs.info("[ClientController] :: canConsult :: true");
				canConsult = true;
			}
		}
		
		return canConsult;
	}
		
}
