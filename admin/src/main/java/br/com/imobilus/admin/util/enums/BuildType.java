package br.com.imobilus.admin.util.enums;

public enum BuildType {
	HOUSE("Casa"),
	APPARTAMENT("Apartamento");
	
	private String value;
	
	BuildType(String value) {
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
