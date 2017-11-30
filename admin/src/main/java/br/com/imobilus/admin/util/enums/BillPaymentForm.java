package br.com.imobilus.admin.util.enums;

public enum BillPaymentForm {
	AVISTA("a vista"),
	APRAZO("a prazo"),
	DEBITO("debito"),
	CREDITO("credito"),
	FINANCIADO("financiado");
	
	private String value;
	
	BillPaymentForm(String value) {
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
