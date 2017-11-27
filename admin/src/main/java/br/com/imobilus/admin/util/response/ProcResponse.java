package br.com.imobilus.admin.util.response;

import br.com.imobilus.admin.model.Entity;

/**
 * Auxilia na resposta em processos de atualização do banco de dados
 * @author douglas.f.filho
 *
 */
public class ProcResponse {
	private String message;
	private String status;
	private Entity entity;
	
	private ProcResponse() {
		message = "Server error.";
		status = ""+Status.ERROR;
		entity = null;
	}
	
	public static ProcResponse getInstance() {
		return new ProcResponse();
	}
	
	public enum Status {
		OK("OK"),
		ERROR("ERROR"),
		WARNING("WARNNING");
		
		private String value;
		
		private Status(String value) {
			this.value = value;
		}
		
		public String get() {
			return this.value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatus(Status status) {
		this.status = status.get();
	}
	
	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "ProcResponse [message=" + message + ", status=" + status + ", entity=" + entity + "]";
	}
}
