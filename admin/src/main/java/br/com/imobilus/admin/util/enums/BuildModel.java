package br.com.imobilus.admin.util.enums;

public enum BuildModel {
	RENT("Aluguel"),
	NEW("Novo"),
	WORN("Usado");
	
	private String value;
	
	BuildModel(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
