package br.com.imobilus.admin.dao;

import java.util.List;

import br.com.imobilus.admin.model.Client;

public interface ClientDao extends DAO<Client> {
	public List<Client> listClientsWithFilter(Client filter) throws Exception;
}
