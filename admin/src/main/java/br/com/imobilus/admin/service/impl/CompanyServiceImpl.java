package br.com.imobilus.admin.service.impl;

import java.util.Calendar;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobilus.admin.dao.CompanyDao;
import br.com.imobilus.admin.model.Company;
import br.com.imobilus.admin.service.CompanyService;
import br.com.imobilus.admin.util.Logs;

@Service
@Transactional
public class CompanyServiceImpl extends GenericService<Company> implements CompanyService {
	
	@Autowired
	CompanyDao companyDao;
	
	@Override
	public Company create(Company company) throws Exception {
		Company createdCompany = null;
		
		Company collapse = getByName(company);
		if(collapse != null) {
			Logs.info("[CompanyServiceImpl] :: create :: Error trying to create company: There's a company already set with the same name in database.");
			throw new Exception("Já existe uma construtora cadastrada com este nome.");
		}
		else {
			company.setCreatedAt(Calendar.getInstance());
			company.setUpdatedBy(company.getCreatedBy());
			company.setUpdatedAt(Calendar.getInstance());
			company.setId(null);
			
			createdCompany = companyDao.create(company);
			Logs.info("[CompanyServiceImpl] :: create :: Success!");
		}
		
		return createdCompany;
	}
	
	@Override
	public Company update(Company company) throws Exception {
		Company updatedCompany = null;
		
		Company collapse = getByName(company);
		if(collapse != null && !collapse.getId().equals(company.getId())) {
			Logs.info("[CompanyServiceImpl] :: update :: Error trying to update company: There's a company already set with the same name in database.");
			throw new Exception("Já existe uma construtora cadastrada com este nome.");
		}
		else {
			company.setUpdatedAt(Calendar.getInstance());
			
			updatedCompany = companyDao.update(company);
			Logs.info("[CompanyServiceImpl] :: update :: Success!");
		}
		
		return updatedCompany;
	}
	
	private Company getByName(Company company) {
		Company found = null;
		
		try {
			found = companyDao.getByName(company.getName());
			Logs.info("[CompanyServiceImpl] :: getByName :: Got company with the name '"+company.getName()+"'");
		} catch(NoResultException e) {
			Logs.info("[CompanyServiceImpl] :: getByName :: Error trying to retrieve company by name: No company found");
		} catch(Exception e) {
			Logs.info("[CompanyServiceImpl] :: getByName :: Error trying to retrieve company by name:");
			e.printStackTrace();
		}
		
		return found;
	}
	
}
