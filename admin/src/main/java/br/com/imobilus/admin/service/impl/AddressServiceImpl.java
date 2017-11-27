package br.com.imobilus.admin.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobilus.admin.dao.AddressDao;
import br.com.imobilus.admin.model.Address;
import br.com.imobilus.admin.service.AddressService;
import br.com.imobilus.admin.util.Logs;

@Service
@Transactional
public class AddressServiceImpl extends GenericService<Address> implements AddressService{
	@Autowired
	AddressDao addressDao;
	
	@Override
	public Address create(Address address) throws Exception {
		Address createdAddress = null;
		
		Address collapse = getByCep(address);
		if(collapse != null) {
			Logs.info("[AddressServiceImpl] :: create :: Error trying to create address setting: There's a setting already set with the same cep in database.");
			throw new Exception("Já existe endereço cadastrado com este cep.");
		}
		else {
			address.setCreatedAt(Calendar.getInstance());
			address.setUpdatedAt(Calendar.getInstance());
			address.setUpdatedBy(address.getCreatedBy());
			
			createdAddress = addressDao.create(address);
			Logs.info("[AddressServiceImpl] :: create :: Success!");
		}
		return createdAddress;
	}
	
	@Override
	public Address update(Address address) throws Exception {
		Address updatedAddress = null;
		
		Address collapse = getByCep(address);
		if(collapse != null && !(collapse.getCep().equals(address.getCep()))) {
			Logs.info("[AddressServiceImpl] :: update :: Error trying to update address setting: There's a setting already set with the same cep in database.");
			throw new Exception("Já existe endereço cadastrado com este cep.");
		}
		else {
			address.setUpdatedAt(Calendar.getInstance());
			updatedAddress = addressDao.update(address);
			Logs.info("[AddressServiceImpl] :: update :: Success!");
		}
		return updatedAddress;
	}
	
	private Address getByCep(Address address) {
		Address found = null;
		Long cep = new Long(0);
		
		try {
			cep = address.getCep();
			found = addressDao.getById(cep);
		} catch(NoResultException e) {
			Logs.info("[AddressServiceImpl] :: getByCep :: Error trying to retrieve address by cep: No address found with the cep '"+cep+"'");
		} catch(Exception e) {
			Logs.info("[AddressServiceImpl] :: getByCep :: Error trying to retrieve address by cep:");
			e.printStackTrace();
		}
		
		return found;
	}

	@Override
	public List<Address> listByAddress(String address) throws Exception {
		List<Address> listedAddresses = addressDao.listByAddress(address);
		return listedAddresses;
	}
}
