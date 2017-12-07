package br.com.imobilus.admin.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.com.imobilus.admin.dao.AchievementDao;
import br.com.imobilus.admin.model.Achievement;
import br.com.imobilus.admin.util.filters.AchievementFilter;

public class AchievementDaoImpl extends GenericDAO<Achievement> implements AchievementDao  {

	@Override
	public List<Achievement> listAchievementsWithFilter(AchievementFilter filter) throws Exception {
		String queryString = "select a from Achievement as a";
		Query query = manager.createQuery(queryString);
		
		if(filter != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("select a from Achievement as a where 1 = 1");
			sb.append(" and a.name like :name");
			sb.append(" and a.company.name like :company");
			if(filter.getCep() != null) {
				sb.append(" and a.address.cep = :cep");
			}
			sb.append(" and a.address.address = :address");
			sb.append(" and a.address.district like :district");
			sb.append(" and a.address.city like :city");
			sb.append(" and a.address.state like :state");
			if(filter.getStartAtInitialInterval() != null) {
				sb.append(" and a.startAt >= :startAtInitialInterval");
			}
			if(filter.getStartAtFinalInterval() != null) {
				sb.append(" and a.startAt <= :startAtFinalInterval");
			}
			if(filter.getEndAtInitialInterval() != null) {
				sb.append(" and a.endAt >= :endAtInitialInterval");
			}
			if(filter.getEndAtFinalInterval() != null) {
				sb.append(" and a.endAt <= :endAtFinalInterval");
			}
			if(filter.getSocialElevators() != null && filter.getSocialElevators() > 0) {
				sb.append(" and a.socialElevators <= :socialElevators");
			}
			if(filter.getServiceElevators() != null && filter.getServiceElevators() > 0) {
				sb.append(" and a.serviceElevators <= :serviceElevators");
			}
			if(filter.hasAccessibility() != null) {
				sb.append(" and a.hasAccessibility <= :hasAccessibility");
			}
			
			queryString = sb.toString();
			query = manager.createQuery(queryString);
			
			query.setParameter("name", filter.getName());
			query.setParameter("company", filter.getCompany());
			if(filter.getCep() != null) {
				query.setParameter("cep", filter.getName());
			}
			query.setParameter("address", filter.getAddress());
			query.setParameter("district", filter.getDistrict());
			query.setParameter("city", filter.getCity());
			query.setParameter("state", filter.getState());
			if(filter.getStartAtInitialInterval() != null) {
				query.setParameter("startAtInitialInterval", filter.getStartAtInitialInterval());
			}
			if(filter.getStartAtFinalInterval() != null) {
				query.setParameter("startAtFinalInterval", filter.getStartAtFinalInterval());
			}
			if(filter.getEndAtInitialInterval() != null) {
				query.setParameter("endAtInitialInterval", filter.getEndAtInitialInterval());
			}
			if(filter.getEndAtFinalInterval() != null) {
				query.setParameter("endAtFinalInterval", filter.getEndAtFinalInterval());
			}
			if(filter.getSocialElevators() != null && filter.getSocialElevators() > 0) {
				query.setParameter("socialElevators", filter.getSocialElevators());
			}
			if(filter.getServiceElevators() != null && filter.getServiceElevators() > 0) {
				query.setParameter("serviceElevators", filter.getServiceElevators());
			}
			if(filter.hasAccessibility() != null) {
				query.setParameter("hasAccessibility", filter.hasAccessibility());
				sb.append(" and a.hasAccessibility <= :hasAccessibility");
			}
		}
		
		@SuppressWarnings("unchecked")
		List<Achievement> list = query.getResultList();
		return list;
	}

}
