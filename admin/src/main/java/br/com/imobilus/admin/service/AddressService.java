package br.com.imobilus.admin.service;

import java.util.List;

import br.com.imobilus.admin.model.Address;

public interface AddressService extends Service<Address>{
	public List<Address> listByAddress(String address) throws Exception;
}
