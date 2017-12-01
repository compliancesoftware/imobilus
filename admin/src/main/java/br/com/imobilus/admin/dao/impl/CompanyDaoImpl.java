package br.com.imobilus.admin.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.imobilus.admin.dao.CompanyDao;
import br.com.imobilus.admin.model.Company;

@Repository
public class CompanyDaoImpl extends GenericDAO<Company> implements CompanyDao {

	@Override
	public Company getByName(String name) throws Exception {
		Query query = manager.createQuery("select c from Company as c where c.name = :name");
		query.setParameter("name", name);
		Company company = (Company) query.getSingleResult();
		return company;
	}
	
}
