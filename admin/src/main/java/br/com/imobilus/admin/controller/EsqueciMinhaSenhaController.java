package br.com.imobilus.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobilus.admin.model.Mailing;
import br.com.imobilus.admin.model.User;
import br.com.imobilus.admin.service.MailingService;
import br.com.imobilus.admin.service.UserService;
import br.com.imobilus.admin.util.EmailUtil;
import br.com.imobilus.admin.util.Logs;
import br.com.imobilus.admin.util.response.ProcResponse;
import br.com.imobilus.admin.util.response.ProcResponse.Status;

@RestController
@RequestMapping("esqueciminhasenha")
public class EsqueciMinhaSenhaController {
	
	@Autowired
	MailingService mailingService;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<ProcResponse> esqueciMinhaSenha(String email) {
		ProcResponse response = ProcResponse.getInstance();
		ResponseEntity<ProcResponse> httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		User updatedUser = null;
		String oldPassword = null;
		
		try {
			Logs.info("[EsqueciMinhaSenhaController] :: esqueciMinhaSenha :: Sending password by e-mail...");
			Logs.info("[EsqueciMinhaSenhaController] :: esqueciMinhaSenha :: Retrieving user by e-mail: " + email);
			
			User found = userService.getByEmail(email);
			Logs.info("[EsqueciMinhaSenhaController] :: esqueciMinhaSenha :: User found: " + found);
			
			oldPassword = found.getPassword();
			
			if(found != null) {
				long now = System.currentTimeMillis();
				long afterNow = System.currentTimeMillis() * 2;
				
				long newPassword = (now + afterNow)/2;
				
				found.setPassword(""+newPassword);
				
				updatedUser = userService.update(found);
				
				List<Mailing> mailingSettings = mailingService.retrieve();
				
				String assunto = "Imobilus - RESET DE SENHA";
				String texto = "Olá, você solicitou reset de sua senha de acesso. Sua nova senha é: " + updatedUser.getPassword();
				
				EmailUtil.enviar(email, assunto, texto, mailingSettings);
				
				response.setMessage("E-mail enviado com êxito!");
				response.setStatus(Status.OK);
				response.setEntity(updatedUser);
				httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.OK);
			}
		} catch(Exception e) {
			Logs.info("[EsqueciMinhaSenhaController] :: esqueciMinhaSenha :: Error trying to send password by e-mail:");
			e.printStackTrace();
			
			if(updatedUser != null) {
				updatedUser.setPassword(oldPassword);
				try {
					userService.update(updatedUser);
				} catch (Exception ex) {
					Logs.info("[EsqueciMinhaSenhaController] :: esqueciMinhaSenha :: Error trying to update user after crash:");
					ex.printStackTrace();
				}
			}
			
			response.setStatus(Status.ERROR);
			response.setMessage(e.getLocalizedMessage());
			
			httpResponse = new ResponseEntity<ProcResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return httpResponse;
	}
}
