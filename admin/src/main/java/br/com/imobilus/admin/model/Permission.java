package br.com.imobilus.admin.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.imobilus.admin.util.Rules;

/**
 * Permissões de usuário do sistema
 * @author douglas.f.filho
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="permission")
public class Permission implements br.com.imobilus.admin.model.Entity {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "description", nullable = false, unique = true, length = 150)
	private String description;
	
	@Column(name = "rules", nullable = false, length = 8000)
	private String rules;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRules() {
		return rules;
	}

	public List<Rules> getRulesAsList() {
		return Rules.getRulesAsList(rules);
	}
	
	public void setRules(String rules) {
		this.rules = rules;
	}
	
	public void setRules(List<Rules> rules) {
		this.rules = Rules.getRulesAsString(rules);
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", description=" + description + ", rules=" + rules + "]";
	}
	
}
