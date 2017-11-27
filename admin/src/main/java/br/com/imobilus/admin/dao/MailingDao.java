package br.com.imobilus.admin.dao;

import br.com.imobilus.admin.model.Mailing;

public interface MailingDao extends DAO<Mailing> {
	public Mailing getByKey(String key);
}
