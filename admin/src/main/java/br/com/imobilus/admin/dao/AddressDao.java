package br.com.imobilus.admin.dao;

import java.util.List;

import br.com.imobilus.admin.model.Address;

public interface AddressDao extends DAO<Address> {
	public List<Address> listByAddress(String filter) throws Exception;
}
