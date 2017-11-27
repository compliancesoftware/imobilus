package br.com.imobilus.admin.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobilus.admin.dao.ClientDao;
import br.com.imobilus.admin.model.Client;
import br.com.imobilus.admin.service.ClientService;
import br.com.imobilus.admin.util.Logs;

@Service
@Transactional
public class ClientServiceImpl extends GenericService<Client> implements ClientService{
	
	@Autowired
	ClientDao clientDao;
	
	@Override
	public Client create(Client client) throws Exception {
		Client createdClient = null;
		
		Client collapse = getByCpf(client.getCpf());
		if(collapse != null) {
			Logs.info("[ClientServiceImpl] :: create :: Error trying to create client: There's a client already set with the same cpf in database.");
			throw new Exception("Já existe cliente cadastrado com este CPF.");
		}
		else {
			collapse = getByRg(client.getRg());
			if(collapse != null) {
				Logs.info("[ClientServiceImpl] :: create :: Error trying to create client: There's a client already set with the same rg in database.");
				throw new Exception("Já existe cliente cadastrado com este RG.");
			}
			else {
				client.setCreatedAt(Calendar.getInstance());
				client.setUpdatedBy(client.getCreatedBy());
				client.setUpdatedAt(Calendar.getInstance());
				client.setId(null);
				
				String p1 = client.getPhone1();
				String p2 = client.getPhone2();
				
				if(p1 != null && !p1.equals("") && (p2 == null || p2.equals(""))) {
					client.setPhone2(p1);
				}
				
				if(p2 != null && !p2.equals("") && (p1 == null || p1.equals(""))) {
					client.setPhone1(p2);
				}
				
				createdClient = clientDao.create(client);
				Logs.info("[ClientServiceImpl] :: create :: Success!");
			}
		}
		return createdClient;
	}
	
	@Override
	public Client update(Client client) throws Exception {
		Client updatedClient = null;
		
		Client collapse = getByCpf(client.getCpf());
		if(collapse != null && !(collapse.getId().equals(client.getId()))) {
			Logs.info("[ClientServiceImpl] :: update :: Error trying to update client: There's a client already set with the same cpf in database.");
			throw new Exception("Já existe cliente cadastrado com este CPF.");
		}
		else {
			collapse = getByRg(client.getRg());
			if(collapse != null && !(collapse.getId().equals(client.getId()))) {
				Logs.info("[ClientServiceImpl] :: update :: Error trying to update client: There's a client already set with the same rg in database.");
				throw new Exception("Já existe cliente cadastrado com este RG.");
			}
			else {
				client.setUpdatedAt(Calendar.getInstance());
				
				updatedClient = clientDao.update(client);
				Logs.info("[ClientServiceImpl] :: update :: Success!");
			}
		}
		return updatedClient;
	
	}

	private Client getByRg(String rg) {
		Client found = null;
		
		try {
			Client filter = new Client();
			filter.setRg(rg);
			
			filter.setCpf("");
			filter.setEmail("");
			filter.setName("");
			filter.setPhone1("");
			
			List<Client> list = clientDao.listClientsWithFilter(filter);
			if(list != null && list.size() > 0) {
				found = list.get(0);
				Logs.info("[ClientServiceImpl] :: getByRg :: Got client with the rg '"+rg+"'");
			}
		} catch(Exception e) {
			Logs.info("[ClientServiceImpl] :: getByRg :: Error trying to retrieve client by rg:");
			e.printStackTrace();
		}
		
		return found;
	}
	
	private Client getByCpf(String cpf) {
		Client found = null;
		
		try {
			Client filter = new Client();
			filter.setCpf(cpf);
			
			filter.setRg("");
			filter.setEmail("");
			filter.setName("");
			filter.setPhone1("");
			
			List<Client> list = clientDao.listClientsWithFilter(filter);
			if(list != null && list.size() > 0) {
				found = list.get(0);
				Logs.info("[ClientServiceImpl] :: getByCpf :: Got client with the cpf '"+cpf+"'");
			}
		} catch(Exception e) {
			Logs.info("[ClientServiceImpl] :: getByCpf :: Error trying to retrieve client by cpf:");
			e.printStackTrace();
		}
		
		return found;
	}

	@Override
	public List<Client> listClientsWithFilter(Client filter) throws Exception {
		List<Client> list = null;
		
		try {
			list = clientDao.listClientsWithFilter(filter);
		} catch(NoResultException e) {
			Logs.info("[ClientServiceImpl] :: listClientsWithFilter :: Error trying to retrieve list of clients with filter: No client found with the filter '"+filter.toString()+"'");
			throw new Exception("Cliente não encontrado.");
		} catch(Exception e) {
			Logs.info("[ClientServiceImpl] :: listClientsWithFilter :: Error trying to retrieve list of clients with filter:");
			e.printStackTrace();
			throw new Exception("Erro ao tentar listar clientes.");
		}
		
		return list;
	}
	
}
