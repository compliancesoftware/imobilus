package br.com.douglasfernandes.dataservices.dao.interfaces;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.douglasfernandes.dataservices.entities.Mailing;

public interface MailingDao {
	public void cadastrar(Mailing mailing) throws Exception;
	public void atualizar(Mailing mailing) throws Exception;
	public void remover(long id) throws Exception;
	
	public Mailing pegarPorId(long id) throws Exception;
	public Mailing pegarPorNome(String nome) throws Exception, NoResultException;
	
	public List<Mailing> listar() throws Exception;
}
