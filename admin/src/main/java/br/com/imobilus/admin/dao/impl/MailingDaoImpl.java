package br.com.imobilus.admin.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.imobilus.admin.dao.MailingDao;
import br.com.imobilus.admin.model.Mailing;

@Repository
public class MailingDaoImpl extends GenericDAO<Mailing> implements MailingDao {

	@Override
	public Mailing getByKey(String key) {
		Query query = manager.createQuery("select m from Mailing as m where m.key = :key");
		query.setParameter("key", key);
		
		Mailing found = (Mailing) query.getSingleResult();
		return found;
	}

}
