package br.com.imobilus.admin.util.enums;

public enum FloorCategory {
	DUPLEX("Duplex"),
	TRIPLEX("Triplex");
	
	private String value;
	
	FloorCategory(String value) {
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
