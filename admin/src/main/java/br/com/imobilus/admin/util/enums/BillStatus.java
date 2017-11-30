package br.com.imobilus.admin.util.enums;

public enum BillStatus {
	PAGA(5),
	PENDENTE(4),
	CANCELADA(3),
	AGENDADA(2),
	CONTESTADA(1);
	
	private Integer statusValue = 4;
	
	BillStatus(Integer statusValue) {
		this.statusValue = statusValue;
	}
	
	public Integer getValue() {
		return this.statusValue;
	}
	
	public static BillStatus valueOf(Integer status) {
		switch(status) {
			case 5:
				return BillStatus.PAGA;
			case 4:
				return BillStatus.PENDENTE;
			case 3:
				return BillStatus.CANCELADA;
			case 2:
				return BillStatus.AGENDADA;
			case 1:
				return BillStatus.CONTESTADA;
			default:
				return BillStatus.PENDENTE;
		}
	}
}
