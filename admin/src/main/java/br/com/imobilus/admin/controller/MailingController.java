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

import br.com.imobilus.admin.model.Mailing;
import br.com.imobilus.admin.model.User;
import br.com.imobilus.admin.service.MailingService;
import br.com.imobilus.admin.service.UserService;
import br.com.imobilus.admin.util.Logs;
import br.com.imobilus.admin.util.Rules;
import br.com.imobilus.admin.util.response.ProcResponse;
import br.com.imobilus.admin.util.response.ProcResponse.Status;

@RestController
@RequestMapping("mailing")
public class MailingController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailingService mailingService;
	
	@PostMapping(value = "create", consumes = "application/json")
	public ResponseEntity<ProcResponse> createMailingSetting(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Mailing mailing) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[MailingController] :: createMailingSetting :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[MailingController] :: createMailingSetting :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[MailingController] :: createMailingSetting :: Creating mailing setting: "+mailing);
				mailing.setCreatedBy(authenticatedUser);
				Mailing createdMailing = mailingService.create(mailing);
				Logs.info("[MailingController] :: createMailingSetting :: Created mailing setting: "+createdMailing);
				
				response.setMessage("Configuração criada com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(createdMailing);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[MailingController] :: createMailingSetting :: Error trying to create mailing setting: User isn't a system administrator.");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[MailingController] :: createMailingSetting :: Error trying to create mailing setting:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value= "update", consumes = "application/json")
	public ResponseEntity<ProcResponse> updateMailingSetting(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Mailing mailing) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[MailingController] :: updateMailingSetting :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[MailingController] :: updateMailingSetting :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[MailingController] :: updateMailingSetting :: Updating mailing setting: "+mailing);
				mailing.setUpdatedBy(authenticatedUser);
				Mailing updatedAddress = mailingService.update(mailing);
				Logs.info("[MailingController] :: updateMailingSetting :: Updated mailing setting: "+updatedAddress);
				
				response.setMessage("Configuração atualizada com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(updatedAddress);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[MailingController] :: updateMailingSetting :: Error trying to update mailing setting: User isn't a system administrator.");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[MailingController] :: updateMailingSetting :: Error trying to update mailing setting:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "delete", consumes = "application/json")
	public ResponseEntity<ProcResponse> deleteMailingSetting(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Mailing mailing) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[MailingController] :: deleteMailingSetting :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[MailingController] :: deleteMailingSetting :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[MailingController] :: deleteMailingSetting :: Deleting mailing setting: "+mailing);
				mailingService.delete(mailing.getId());
				Logs.info("[MailingController] :: deleteMailingSetting :: Mailing setting deleted");
				
				response.setMessage("Configuração excluida com êxito.");
				response.setStatus(Status.OK);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[MailingController] :: deleteMailingSetting :: Error trying to delete mailing setting: User isn't a system administrator.");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[MailingController] :: deleteMailingSetting :: Error trying to delete mailing setting: ");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@GetMapping("retrieve")
	public ResponseEntity<List<Mailing>> retriveMailingSettings(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword) {
		List<Mailing> settings = new ArrayList<Mailing>();
		ResponseEntity<List<Mailing>> httpResponse = new ResponseEntity<List<Mailing>>(settings, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[MailingController] :: retriveMailingSettings :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[MailingController] :: retriveMailingSettings :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[MailingController] :: retriveMailingSettings :: Retriving settings...");
				settings = mailingService.retrieve();
				
				if(settings != null && settings.size() > 0) {
					Logs.info("[MailingController] :: retriveMailingSettings :: Retrived "+settings.size()+" settings.");
					httpResponse = new ResponseEntity<List<Mailing>>(settings, HttpStatus.OK);
				}
				else {
					Logs.info("[MailingController] :: retriveMailingSettings :: No settings to retrieve.");
					httpResponse = new ResponseEntity<List<Mailing>>(settings, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				Logs.info("[MailingController] :: retriveMailingSettings :: Error trying to delete mailing setting: User isn't a system administrator.");
				httpResponse = new ResponseEntity<List<Mailing>>(settings, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[MailingController] :: retriveMailingSettings :: Error trying to retrieve settings: ");
			e.printStackTrace();
			httpResponse = new ResponseEntity<List<Mailing>>(settings, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	private boolean haveTheRules(User user) {
		boolean haveRules = false;
		List<Rules> rules = user.getPermission().getRulesAsList();
		
		for(Rules rule : rules) {
			Logs.info("[MailingController] :: haveTheRules :: " + user.getName() + " rule: "+rule.getValue());
			if(rule.equals(Rules.ADMINISTRADOR) || rule.equals(Rules.MARKETING)) {
				Logs.info("[AddressController] :: haveTheRules :: true");
				haveRules = true;
			}
		}
		
		return haveRules;
	}
}
