package br.com.imobilus.admin.service.impl;

import java.util.Calendar;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobilus.admin.dao.MailingDao;
import br.com.imobilus.admin.model.Mailing;
import br.com.imobilus.admin.service.MailingService;
import br.com.imobilus.admin.util.Logs;

@Service
@Transactional
public class MailingServiceImpl extends GenericService<Mailing> implements MailingService{
	
	@Autowired
	MailingDao mailingDao;
	
	@Override
	public Mailing create(Mailing mailing) throws Exception {
		Mailing createdMailing = null;
		
		Mailing collapse = getByKey(mailing);
		if(collapse != null) {
			Logs.info("[MailingServiceImpl] :: create :: Error trying to create new mailing setting: There's a setting already set with the same key in database.");
			throw new Exception("Já existe uma configuração de e-mail cadastrada com esta chave.");
		}
		else {
			mailing.setId(null);
			
			Calendar now = Calendar.getInstance();
			mailing.setCreatedAt(now);
			mailing.setUpdatedAt(now);
			mailing.setUpdatedBy(mailing.getCreatedBy());
			
			createdMailing = mailingDao.create(mailing);
			Logs.info("[MailingServiceImpl] :: create :: Success!");
		}
		return createdMailing;
	}
	
	@Override
	public Mailing update(Mailing mailing) throws Exception {
		Mailing updatedMailing = null;
		
		Mailing collapse = getByKey(mailing);
		if(collapse != null && !(collapse.getId().equals(mailing.getId()))) {
			Logs.info("[MailingServiceImpl] :: update :: Error trying to update mailing setting: There's a setting already set with the same key in database.");
			throw new Exception("Já existe uma configuração de e-mail cadastrada com esta chave.");
		}
		else {
			Calendar now = Calendar.getInstance();
			mailing.setUpdatedAt(now);
			
			updatedMailing = mailingDao.update(mailing);
			Logs.info("[MailingServiceImpl] :: update :: Success!");
		}
		return updatedMailing;
	}
	
	private Mailing getByKey(Mailing mailing) {
		Mailing found = null;
		String key = "";
		
		try {
			key = mailing.getKey();
			found = mailingDao.getByKey(key);
		} catch(NoResultException e) {
			Logs.info("[MailingServiceImpl] :: getByKey :: Error trying to retrieve mailing setting by key: No mailing setting found with the key '"+key+"'");
		} catch(Exception e) {
			Logs.info("[MailingServiceImpl] :: getByKey :: Error trying to retrieve mailing setting by key:");
			e.printStackTrace();
		}
		
		return found;
	}
}
