package br.com.imobilus.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.imobilus.admin.dao.AddressDao;
import br.com.imobilus.admin.model.Address;

@Repository
public class AddressDaoImpl extends GenericDAO<Address>  implements AddressDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> listByAddress(String filter) throws Exception {
		List<Address> list = new ArrayList<Address>();
		
		if(filter != null && filter.length() > 0) {
			Query query = manager.createQuery("select a from Address as a where a.address like :text1 or a.complement like :text2");
			query.setParameter("text1", "%"+filter+"%");
			query.setParameter("text2", "%"+filter+"%");
			list = query.getResultList();
		}
		else {
			Query query = manager.createQuery("select a from Address as a");
			list = query.getResultList();
		}
		
		return list;
	}
	
}
