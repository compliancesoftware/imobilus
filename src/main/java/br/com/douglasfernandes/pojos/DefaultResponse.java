package br.com.douglasfernandes.pojos;

public class DefaultResponse {
	private Status status;
	private String mensagem;
	
	public enum Status{
		ERRO("Erro!"),
		AVISO("Aviso!"),
		SUCESSO("Sucesso!");
		
		private String valor;
		
		public String getValor(){
			return this.valor;
		}
		
		private Status(String valor){
			this.valor = valor;
		}
		
	}
	
	public DefaultResponse(){
		status = Status.ERRO;
		mensagem = Status.ERRO.getValor();
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "DefaultResponse [status=" + status.getValor() + ", mensagem=" + mensagem + "]";
	}
}
