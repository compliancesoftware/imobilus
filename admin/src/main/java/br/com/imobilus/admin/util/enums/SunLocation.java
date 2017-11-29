package br.com.imobilus.admin.util.enums;

public enum SunLocation {
	SUNRISE("Nascente"),
	SUNSET("Poente");
	
	private String value;
	
	SunLocation(String value) {
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
