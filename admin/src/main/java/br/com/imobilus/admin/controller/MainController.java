package br.com.imobilus.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobilus.admin.model.Address;
import br.com.imobilus.admin.model.Client;
import br.com.imobilus.admin.model.Mailing;
import br.com.imobilus.admin.model.Permission;
import br.com.imobilus.admin.model.User;
import br.com.imobilus.admin.service.AddressService;
import br.com.imobilus.admin.service.ClientService;
import br.com.imobilus.admin.service.MailingService;
import br.com.imobilus.admin.service.PermissionService;
import br.com.imobilus.admin.service.UserService;
import br.com.imobilus.admin.util.Logs;
import br.com.imobilus.admin.util.enums.Rules;
import br.com.imobilus.admin.util.response.ProcResponse;
import br.com.imobilus.admin.util.response.ProcResponse.Status;

@RestController
@RequestMapping("main")
public class MainController {

	@Autowired
	UserService userService;
	
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	MailingService mailingService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	ClientService clientService;
	
	@GetMapping
	public ResponseEntity<ProcResponse> firstState() {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			Logs.info("[MainController] :: firstState :: Creating states...");
			
			Logs.info("[MainController] :: firstState :: Creating user...");
			User user = new User();
			user.setName("Administrador");
			user.setPassword("admin");
			user.setPhone1("81996729491");
			user.setPhone2("81988874815");
			user.setEmail("douglasf.filho@gmail.com");
			
			User createdUser = userService.create(user);
			
			if(createdUser != null) {
				Logs.info("[MainController] :: firstState :: Created user: " + createdUser.getName());
				
				Logs.info("[MainController] :: firstState :: Creating permission...");
				Permission permission = new Permission();
				permission.setDescription("Administrador");
				ArrayList<Rules> rules = new ArrayList<Rules>();
				rules.add(Rules.ADMINISTRADOR);
				permission.setRules(rules);
				
				Permission createdPermission = permissionService.create(permission);
				
				if(createdPermission != null) {
					Logs.info("[MainController] :: firstState :: Created permission: " + createdPermission.getDescription());
					
					Logs.info("[MainController] :: firstState :: Updating main user...");
					
					createdUser.setPermission(createdPermission);
					
					User updatedUser = userService.update(createdUser);
					
					if(updatedUser != null) {
						Logs.info("[MainController] :: firstState :: User up to date: " + updatedUser);
						Logs.info("[MainController] :: firstState :: Adding mailing settings...");
						
						Mailing mailSmtpAuth = new Mailing();
						mailSmtpAuth.setKey("mail.smtp.auth");
						mailSmtpAuth.setValue("true");
						mailSmtpAuth.setCreatedBy(updatedUser);
						mailSmtpAuth.setUpdatedBy(updatedUser);
						
						Mailing mailSmtpStarttlsEnable = new Mailing();
						mailSmtpStarttlsEnable.setKey("mail.smtp.starttls.enable");
						mailSmtpStarttlsEnable.setValue("true");
						mailSmtpStarttlsEnable.setCreatedBy(updatedUser);
						mailSmtpStarttlsEnable.setUpdatedBy(updatedUser);
						
						Mailing mailSmtpHost = new Mailing();
						mailSmtpHost.setKey("mail.smtp.host");
						mailSmtpHost.setValue("smtp.gmail.com");
						mailSmtpHost.setCreatedBy(updatedUser);
						mailSmtpHost.setUpdatedBy(updatedUser);
						
						Mailing mailSmtpPort = new Mailing();
						mailSmtpPort.setKey("mail.smtp.port");
						mailSmtpPort.setValue("587");
						mailSmtpPort.setCreatedBy(updatedUser);
						mailSmtpPort.setUpdatedBy(updatedUser);
						
						Mailing userName = new Mailing();
						userName.setKey("username");
						userName.setValue("true");
						userName.setCreatedBy(updatedUser);
						userName.setUpdatedBy(updatedUser);
						
						Mailing passoword = new Mailing();
						passoword.setKey("password");
						passoword.setValue("$6745#3233@Imobi");
						passoword.setCreatedBy(updatedUser);
						passoword.setUpdatedBy(updatedUser);
						
						List<Mailing> mailingSettings = new ArrayList<Mailing>();
						mailingSettings.add(mailSmtpAuth);
						mailingSettings.add(mailSmtpStarttlsEnable);
						mailingSettings.add(mailSmtpHost);
						mailingSettings.add(mailSmtpPort);
						mailingSettings.add(userName);
						mailingSettings.add(passoword);
						
						Mailing last = null;
						
						for(Mailing item : mailingSettings) {
							last = mailingService.create(item);
						}
						
						if(last != null) {
							Logs.info("[MainController] :: firstState :: Created mailing settings.");
							
							Logs.info("[MainController] :: firstState :: Creating address...");
							
							Address address = new Address();
							address.setCep(50030150L);
							address.setAddress("Avenida Alfredo Lisboa");
							address.setComplement("Praça do Marco Zero");
							address.setDistrict("Recife");
							address.setCity("Recife");
							address.setState("Pernambuco");
							address.setCreatedBy(updatedUser);
							
							Address createdAddress = addressService.create(address);
							
							if(createdAddress != null) {
								Logs.info("[MainController] :: firstState :: Address creation success.");
								Logs.info("[MainController] :: firstState :: Creating test client...");
								
								Client testClient = new Client();
								testClient.setName("Douglas Fernandes");
								testClient.setCpf("08468331406");
								testClient.setRg("7898636");
								testClient.setPhone1("81996729491");
								testClient.setEmail("douglasf.filho@gmail.com");
								testClient.setAddressId(createdAddress);
								testClient.setCreatedBy(updatedUser);
								
								Client createdClient = clientService.create(testClient);
								
								if(createdClient != null) {
									Logs.info("[MainController] :: firstState :: Test client creation success.");
									response.setMessage("Sistema iniciado com êxito!");
									response.setStatus(Status.OK);
									httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
									
									Logs.info("[MainController] :: firstState :: States finished successfully!");
								}
								else {
									Logs.info("[MainController] :: firstState :: Test client creation fails, look up the log.");
									Logs.info("[MainController] :: firstState :: States finished with errors!");
									
									httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
								}
							}
							else {
								Logs.info("[MainController] :: firstState :: Address creation fails, look up the log.");
								Logs.info("[MainController] :: firstState :: States finished with errors!");
								
								httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
							}
						}
						else {
							Logs.info("[MainController] :: firstState :: Mailing settings creation fails, look up the log.");
							Logs.info("[MainController] :: firstState :: States finished with errors!");
							
							httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
						}
					}
					else {
						Logs.info("[MainController] :: firstState :: User update fails, look up the log.");
						Logs.info("[MainController] :: firstState :: States finished with errors!");
						
						httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				else {
					Logs.info("[MainController] :: firstState :: Permission creation fails, look up the log.");
					Logs.info("[MainController] :: firstState :: States finished with errors!");
					
					httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				Logs.info("[MainController] :: firstState :: User creation fails, look up the log.");
				Logs.info("[MainController] :: firstState :: States finished with errors!");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[MainController] :: firstState :: Error trying to do first state to system:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
}
