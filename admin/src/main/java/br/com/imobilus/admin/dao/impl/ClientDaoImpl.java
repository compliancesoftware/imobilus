package br.com.imobilus.admin.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.imobilus.admin.dao.ClientDao;
import br.com.imobilus.admin.model.Client;

@Repository
public class ClientDaoImpl extends GenericDAO<Client> implements ClientDao {
	@Override
	public List<Client> listClientsWithFilter(Client filter) throws Exception {
		String queryString = "select c from Client as c";
		Query query = manager.createQuery(queryString);
		
		if(filter != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("select c from Client as c where 1 = 1");
			sb.append(" and c.name like :name");
			sb.append(" and c.cpf like :cpf");
			sb.append(" and c.rg like :rg");
			sb.append(" and c.email like :email");
			sb.append(" and (c.phone1 like :phone1");
			sb.append(" or c.phone2 like :phone2)");
			
			queryString = sb.toString();
			query = manager.createQuery(queryString);
			
			query.setParameter("name", "%"+filter.getName()+"%");
			query.setParameter("cpf", "%"+filter.getCpf()+"%");
			query.setParameter("rg", "%"+filter.getRg()+"%");
			query.setParameter("email", "%"+filter.getEmail()+"%");
			query.setParameter("phone1", "%"+filter.getPhone1()+"%");
			query.setParameter("phone2", "%"+filter.getPhone1()+"%");
		}
		
		@SuppressWarnings("unchecked")
		List<Client> list = query.getResultList();
		return list;
	}
}
