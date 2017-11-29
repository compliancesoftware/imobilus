package br.com.imobilus.admin.util.enums;

import java.util.ArrayList;
import java.util.List;

public enum Rules {
	ADMINISTRADOR("Administrador"),
	CORRETOR("Corretor"),
	MARKETING("Marketing"),
	FINANCEIRO("Financeiro"),
	TESTE("Teste");
	
	private String value;
	
	Rules(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static ArrayList<Rules> getRulesAsList(String rule) {
		ArrayList<Rules> rulesValue = new ArrayList<Rules>();
		
		for(Rules rules : Rules.values()) {
			if(rule.contains(rules.getValue())) {
				rulesValue.add(rules);
			}
		}
		
		return rulesValue;
	}
	
	public static String getRulesAsString(List<Rules> rules) {
		String rulesAsString = "";
		
		for(Rules rule : rules) {
			rulesAsString += rule.getValue();
			rulesAsString += ",";
		}
		
		rulesAsString = rulesAsString.substring(0, rulesAsString.length() - 1);
		
		return rulesAsString;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
}
