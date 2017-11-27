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

import br.com.imobilus.admin.model.Address;
import br.com.imobilus.admin.model.User;
import br.com.imobilus.admin.service.AddressService;
import br.com.imobilus.admin.service.UserService;
import br.com.imobilus.admin.util.Logs;
import br.com.imobilus.admin.util.Rules;
import br.com.imobilus.admin.util.response.ProcResponse;
import br.com.imobilus.admin.util.response.ProcResponse.Status;

@RestController
@RequestMapping("addresses")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "create", consumes="application/json")
	public ResponseEntity<ProcResponse> createAddress(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Address address) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[AddressController] :: createAddress :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[AddressController] :: createAddress :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[AddressController] :: createAddress :: Creating address: "+address);
				address.setCreatedBy(authenticatedUser);
				Address createdAddress = addressService.create(address);
				Logs.info("[AddressController] :: createAddress :: Created address: "+createdAddress);
				
				response.setMessage("Endereço criado com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(createdAddress);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[AddressController] :: createAddress :: Error trying to create address:");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[AddressController] :: createAddress :: Error trying to create address:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "update", consumes = "application/json")
	public ResponseEntity<ProcResponse> updateAddress(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Address address) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[AddressController] :: updateAddress :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[AddressController] :: updateAddress :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[AddressController] :: updateAddress :: Updating address: "+address);
				address.setUpdatedBy(authenticatedUser);
				Address updatedAddress = addressService.update(address);
				Logs.info("[AddressController] :: updateAddress :: Updated address: "+updatedAddress);
				
				response.setMessage("Endereço atualizado com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(updatedAddress);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[AddressController] :: updateAddress :: Error trying to update address:");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[AddressController] :: updateAddress :: Error trying to update address:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "delete", consumes = "application/json")
	public ResponseEntity<ProcResponse> deleteAddress(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Address address) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[AddressController] :: deleteAddress :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[AddressController] :: deleteAddress :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[AddressController] :: deleteAddress :: Deleting address: "+address);
				addressService.delete(address.getCep());
				Logs.info("[AddressController] :: deleteAddress :: Address deleted.");
				
				response.setMessage("Endereço excluido com êxito.");
				response.setStatus(Status.OK);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[AddressController] :: deleteAddress :: Error trying to delete address:");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[AddressController] :: deleteAddress :: Error trying to delete address: ");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@GetMapping("retrieve")
	public ResponseEntity<List<Address>> retriveAddresses(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, String searchFilter) {
		List<Address> addresses = new ArrayList<Address>();
		ResponseEntity<List<Address>> httpResponse = new ResponseEntity<List<Address>>(addresses, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[AddressController] :: retriveAddresses :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[AddressController] :: retriveAddresses :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[AddressController] :: retriveAddresses :: Retriving addresses...(filter: " + searchFilter + ")");
				addresses = addressService.listByAddress(searchFilter);
				
				if(addresses != null && addresses.size() > 0) {
					Logs.info("[AddressController] :: retriveAddresses :: Retrived "+addresses.size()+" addresses.");
					httpResponse = new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
				}
				else {
					Logs.info("[AddressController] :: retriveAddresses :: No adresses to retrieve.");
					httpResponse = new ResponseEntity<List<Address>>(addresses, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				Logs.info("[AddressController] :: retriveAddresses :: No adresses to retrieve. User on authenticating haven't permissions.");
				httpResponse = new ResponseEntity<List<Address>>(addresses, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[AddressController] :: retriveAddresses :: Error trying to retrieve addresses: ");
			e.printStackTrace();
			httpResponse = new ResponseEntity<List<Address>>(addresses, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	private boolean haveTheRules(User user) {
		boolean haveRules = false;
		List<Rules> rules = user.getPermission().getRulesAsList();
		
		for(Rules rule : rules) {
			Logs.info("[AddressController] :: haveTheRules :: " + user.getName() + " rule: "+rule.getValue());
			if(!rule.equals(Rules.MAILER)) {
				Logs.info("[AddressController] :: haveTheRules :: true");
				haveRules = true;
			}
		}
		
		return haveRules;
	}
}
