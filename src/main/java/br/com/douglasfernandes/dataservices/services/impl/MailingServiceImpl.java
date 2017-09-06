package br.com.douglasfernandes.dataservices.services.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.douglasfernandes.dataservices.dao.interfaces.MailingDao;
import br.com.douglasfernandes.dataservices.entities.Mailing;
import br.com.douglasfernandes.dataservices.services.interfaces.MailingService;
import br.com.douglasfernandes.pojos.DefaultResponse;
import br.com.douglasfernandes.pojos.DefaultResponse.Status;
import br.com.douglasfernandes.utils.Logs;

@Service
@Transactional
public class MailingServiceImpl implements MailingService
{
	@Autowired
	@Qualifier("mailingDaoImpl")
	MailingDao mailingDao;
	
	@Override
	public DefaultResponse cadastrar(Mailing mailing) {
		DefaultResponse response = new DefaultResponse();
		
		Mailing conflito = null;
		
		try{
			conflito = mailingDao.pegarPorNome(mailing.getKey());
		} catch(NoResultException nre){
			Logs.warn("[MailingServiceImpl]::cadastrar::Nao ha conflito de propriedade por nome.");
		} catch(Exception e){
			Logs.warn("[MailingServiceImpl]::cadastrar::Erro ao tentar cadastrar propriedade de mailing: Exception:");
			e.printStackTrace();
		}
		
		try{
			if(conflito != null){
				Logs.warn("[MailingServiceImpl]::cadastrar::Erro ao tentar cadastrar propriedade de mailing: Ja existe.");
				response.setMensagem("Já existe esta propriedade cadastrada.");
				response.setStatus(Status.AVISO);
				
				return response;
			}
			else{
				Logs.info("[MailingServiceImpl]::cadastrar::Cadastrando propriedade: "+mailing.toString());
				
				mailingDao.cadastrar(mailing);
				
				Logs.info("[MailingServiceImpl]::cadastrar::Cadastrado com exito.");
				
				response.setMensagem("Cadastrado com êxito.");
				response.setStatus(Status.SUCESSO);
				
				return response;
			}
		}catch(Exception e){
			Logs.warn("[MailingServiceImpl]::cadastrar::Erro ao tentar cadastrar propriedade de mailing.Exception:");
			e.printStackTrace();
			
			return response;
		}
	}

	@Override
	public DefaultResponse atualizar(Mailing mailing) {
		DefaultResponse response = new DefaultResponse();
		
		Mailing conflito = null;
		
		try{
			conflito = mailingDao.pegarPorNome(mailing.getKey());
		} catch(NoResultException nre){
			Logs.warn("[MailingServiceImpl]::cadastrar::Nao ha conflito de propriedade por nome.");
		} catch(Exception e){
			Logs.warn("[MailingServiceImpl]::cadastrar::Erro ao tentar cadastrar propriedade de mailing: Exception:");
			e.printStackTrace();
		}
		
		try{
			if(conflito != null && conflito.getId() != mailing.getId()){
				Logs.warn("[MailingServiceImpl]::atualizar::Erro ao tentar atualizar propriedade de mailing: Ja existe.");
				response.setMensagem("Já existe esta propriedade cadastrada.");
				response.setStatus(Status.AVISO);
				
				return response;
			}
			else{
				Logs.info("[MailingServiceImpl]::atualizar::Atualizando propriedade: "+mailing.toString());
				
				mailingDao.atualizar(mailing);
				
				Logs.info("[MailingServiceImpl]::atualizar::Atualizado com exito.");
				
				response.setMensagem("Atualizado com êxito.");
				response.setStatus(Status.SUCESSO);
				
				return response;
			}
		}catch(Exception e){
			Logs.warn("[MailingServiceImpl]::atualizar::Erro ao tentar atualizar propriedade de mailing.Exception:");
			e.printStackTrace();
			
			return response;
		}
	}

	@Override
	public DefaultResponse remover(long id) {
		DefaultResponse response = new DefaultResponse();
		
		try{
			mailingDao.remover(id);
			
			Logs.info("[MailingServiceImpl]::remover::propriedade de mailing removida.");
			
			response.setMensagem("Propriedade removida.");
			response.setStatus(Status.SUCESSO);
			
			return response;
		}catch(Exception e){
			Logs.warn("[MailingServiceImpl]::remover::Erro ao tentar remover propriedade de mailing.Exception:");
			e.printStackTrace();
			
			return response;
		}
	}

	@Override
	public List<Mailing> listar() {
		try{
			List<Mailing> lista = mailingDao.listar();
			
			if(lista != null && lista.size() > 0)
				Logs.info("[MailingServiceImpl]::listar::propriedades listadas: "+lista.size());
			else
				Logs.info("[MailingServiceImpl]::listar::nenhuma propriedade listada.");
			
			return lista;
		}catch(Exception e){
			Logs.warn("[MailingServiceImpl]::listar::Erro ao tentar listar propriedades de mailing.Exception:");
			e.printStackTrace();
			
			return null;
		}
	}

	@Override
	public void init() {
		try{
			List<Mailing> presentes = listar();
			if(presentes == null || presentes.size() < 1){
				
				Mailing mailSmtpAuth = new Mailing();
				mailSmtpAuth.setKey("mail.smtp.auth");
				mailSmtpAuth.setValue("true");
				
				Mailing mailSmtpStartTlsEnable = new Mailing();
				mailSmtpStartTlsEnable.setKey("mail.smtp.starttls.enable");
				mailSmtpStartTlsEnable.setValue("true");
				
				Mailing mailSmtpHost = new Mailing();
				mailSmtpHost.setKey("mail.smtp.host");
				mailSmtpHost.setValue("smtp.gmail.com");
				
				Mailing mailSmtpPort = new Mailing();
				mailSmtpPort.setKey("mail.smtp.port");
				mailSmtpPort.setValue("587");
				
				Mailing username = new Mailing();
				username.setKey("username");
				username.setValue("main.compliancesoftware@gmail.com");
				
				Mailing password = new Mailing();
				password.setKey("password");
				password.setValue("$9916do@d");
				
				mailingDao.cadastrar(mailSmtpAuth);
				Logs.info("[MailingServiceImpl]::init::Propriedade cadastrada: "+mailSmtpAuth);
				
				mailingDao.cadastrar(mailSmtpStartTlsEnable);
				Logs.info("[MailingServiceImpl]::init::Propriedade cadastrada: "+mailSmtpStartTlsEnable);
				
				mailingDao.cadastrar(mailSmtpHost);
				Logs.info("[MailingServiceImpl]::init::Propriedade cadastrada: "+mailSmtpHost);
				
				mailingDao.cadastrar(mailSmtpPort);
				Logs.info("[MailingServiceImpl]::init::Propriedade cadastrada: "+mailSmtpPort);
				
				mailingDao.cadastrar(username);
				Logs.info("[MailingServiceImpl]::init::Propriedade cadastrada: "+username);
				
				mailingDao.cadastrar(password);
				Logs.info("[MailingServiceImpl]::init::Propriedade cadastrada: "+password);
			}
			else{
				Logs.info("[MailingServiceImpl]::init::Lista de propriedades já existe.");
			}
		}catch(Exception e){
			Logs.warn("[MailingServiceImpl]::init::Erro ao tentar inicializar informacoes.Exception:");
			e.printStackTrace();
		}
	}

}
