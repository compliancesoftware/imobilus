package br.com.douglasfernandes.dataservices.services.interfaces;

import java.util.List;

import br.com.douglasfernandes.dataservices.entities.Mailing;
import br.com.douglasfernandes.pojos.DefaultResponse;

public interface MailingService {
	public DefaultResponse cadastrar(Mailing mailing);
	public DefaultResponse atualizar(Mailing mailing);
	public DefaultResponse remover(long id);
	public List<Mailing> listar();
	
	public void init();
}
