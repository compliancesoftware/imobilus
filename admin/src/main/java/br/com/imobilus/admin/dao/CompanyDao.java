package br.com.imobilus.admin.dao;

import br.com.imobilus.admin.model.Company;

public interface CompanyDao extends DAO<Company> {
	public Company getByName(String name) throws Exception;
}
