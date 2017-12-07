package br.com.imobilus.admin.dao;

import java.util.List;

import br.com.imobilus.admin.model.Achievement;
import br.com.imobilus.admin.util.filters.AchievementFilter;

public interface AchievementDao extends DAO<Achievement> {
	public List<Achievement> listAchievementsWithFilter(AchievementFilter filter) throws Exception;
}
