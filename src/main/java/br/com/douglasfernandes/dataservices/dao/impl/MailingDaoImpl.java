package br.com.douglasfernandes.dataservices.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.douglasfernandes.dataservices.dao.interfaces.MailingDao;
import br.com.douglasfernandes.dataservices.entities.Mailing;

@Repository
public class MailingDaoImpl implements MailingDao{
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void cadastrar(Mailing mailing) throws Exception {
		manager.persist(mailing);
	}

	@Override
	public void atualizar(Mailing mailing) throws Exception {
		manager.merge(mailing);
	}

	@Override
	public void remover(long id) throws Exception {
		Mailing mailing = pegarPorId(id);
		manager.remove(mailing);
	}

	@Override
	public Mailing pegarPorId(long id) throws Exception {
		Mailing mailing = manager.find(Mailing.class, id);
		return mailing;
	}

	@Override
	public Mailing pegarPorNome(String nome) throws Exception, NoResultException {
		Query q = manager.createQuery("select m from Mailing as m where m.key = :nome");
		q.setParameter("nome", nome);
		
		Mailing mailing = (Mailing)q.getSingleResult();
		return mailing;
	}

	@Override
	public List<Mailing> listar() throws Exception {
		Query q = manager.createQuery("select m from Mailing as m");
		@SuppressWarnings("unchecked")
		List<Mailing> lista = q.getResultList();
		
		return lista;
	}

}
