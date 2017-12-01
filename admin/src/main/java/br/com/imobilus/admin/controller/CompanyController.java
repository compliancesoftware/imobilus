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

import br.com.imobilus.admin.model.Company;
import br.com.imobilus.admin.model.User;
import br.com.imobilus.admin.service.CompanyService;
import br.com.imobilus.admin.service.UserService;
import br.com.imobilus.admin.util.Logs;
import br.com.imobilus.admin.util.enums.Rules;
import br.com.imobilus.admin.util.response.ProcResponse;
import br.com.imobilus.admin.util.response.ProcResponse.Status;

@RestController
@RequestMapping("companies")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "create", consumes="application/json")
	public ResponseEntity<ProcResponse> createCompany(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Company company) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[CompanyController] :: createCompany :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[CompanyController] :: createCompany :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[CompanyController] :: createCompany :: Creating company: " + company);
				company.setCreatedBy(authenticatedUser);
				Company createdCompany = companyService.create(company);
				Logs.info("[CompanyController] :: createCompany :: Created company: " + createdCompany);
				
				response.setMessage("Construtora criada com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(createdCompany);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[CompanyController] :: createCompany :: Error trying to create company:");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[CompanyController] :: createCompany :: Error trying to create company:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}

	@PostMapping(value = "update", consumes = "application/json")
	public ResponseEntity<ProcResponse> updateCompany(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Company company) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[CompanyController] :: updateCompany :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[CompanyController] :: updateCompany :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[CompanyController] :: updateCompany :: Updating company: " + company);
				company.setUpdatedBy(authenticatedUser);
				Company updatedCompany = companyService.update(company);
				Logs.info("[CompanyController] :: updateCompany :: Updated company: " + updatedCompany);
				
				response.setMessage("Construtora atualizada com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(updatedCompany);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[CompanyController] :: updateCompany :: Error trying to update company:");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[CompanyController] :: updateCompany :: Error trying to update company:");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@PostMapping(value = "delete", consumes = "application/json")
	public ResponseEntity<ProcResponse> deleteCompany(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword, @RequestBody Company company) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[CompanyController] :: deleteCompany :: Authenticating user: " + authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[CompanyController] :: deleteCompany :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[CompanyController] :: deleteCompany :: Deleting company: " + company);
				companyService.delete(company.getId());
				Logs.info("[CompanyController] :: deleteCompany :: Company deleted.");
				
				response.setMessage("Construtora excluida com êxito.");
				response.setStatus(Status.OK);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
			else {
				Logs.info("[CompanyController] :: deleteCompany :: Error trying to delete company:");
				
				response.setStatus(Status.ERROR);
				response.setMessage("Falha de autenticação: Usuário não é um administrador do sistema.");
				
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[CompanyController] :: deleteCompany :: Error trying to delete company: ");
			e.printStackTrace();
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	@GetMapping("retrieve")
	public ResponseEntity<List<Company>> retriveCompanies(@RequestHeader("userName") String userName, @RequestHeader("userPassword") String userPassword) {
		List<Company> companies = new ArrayList<Company>();
		ResponseEntity<List<Company>> httpResponse = new ResponseEntity<List<Company>>(companies, HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			User authUser = new User();
			authUser.setName(userName);
			authUser.setPassword(userPassword);
			
			Logs.info("[CompanyController] :: retriveCompanies :: Authenticating user: "+authUser.getName());
			User authenticatedUser = userService.authenticate(authUser);
			
			if(haveTheRules(authenticatedUser)) {
				Logs.info("[CompanyController] :: retriveCompanies :: User authenticate success: " + authenticatedUser);
				
				Logs.info("[CompanyController] :: retriveCompanies :: Retriving companies...");
				companies = companyService.retrieve();
				
				if(companies != null && companies.size() > 0) {
					Logs.info("[CompanyController] :: retriveCompanies :: Retrived "+companies.size()+" companies.");
					httpResponse = new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
				}
				else {
					Logs.info("[CompanyController] :: retriveCompanies :: No companies to retrieve.");
					httpResponse = new ResponseEntity<List<Company>>(companies, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				Logs.info("[CompanyController] :: retriveCompanies :: No comnpanies to retrieve. User on authenticating haven't permissions.");
				httpResponse = new ResponseEntity<List<Company>>(companies, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(Exception e) {
			Logs.info("[CompanyController] :: retriveCompanies :: Error trying to retrieve companies: ");
			e.printStackTrace();
			httpResponse = new ResponseEntity<List<Company>>(companies, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
	
	private boolean haveTheRules(User user) {
		boolean haveRules = false;
		List<Rules> rules = user.getPermission().getRulesAsList();
		
		for(Rules rule : rules) {
			Logs.info("[CompanyController] :: haveTheRules :: " + user.getName() + " rule: "+rule.getValue());
			if(rule.equals(Rules.ADMINISTRADOR) || rule.equals(Rules.FINANCEIRO)) {
				Logs.info("[CompanyController] :: haveTheRules :: true");
				haveRules = true;
			}
		}
		
		return haveRules;
	}
	
}
