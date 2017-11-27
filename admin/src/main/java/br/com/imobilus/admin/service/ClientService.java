package br.com.imobilus.admin.service;

import java.util.List;

import br.com.imobilus.admin.model.Client;

public interface ClientService extends Service<Client> {
	public List<Client> listClientsWithFilter(Client filter) throws Exception;
}
