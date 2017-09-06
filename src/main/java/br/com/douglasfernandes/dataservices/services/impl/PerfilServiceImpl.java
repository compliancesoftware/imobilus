package br.com.douglasfernandes.dataservices.services.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.douglasfernandes.dataservices.dao.interfaces.PerfilDao;
import br.com.douglasfernandes.dataservices.entities.Perfil;
import br.com.douglasfernandes.dataservices.services.interfaces.PerfilService;
import br.com.douglasfernandes.pojos.DefaultResponse;
import br.com.douglasfernandes.pojos.DefaultResponse.Status;
import br.com.douglasfernandes.utils.Logs;

@Service
@Transactional
public class PerfilServiceImpl implements PerfilService{
	
	@Autowired
	@Qualifier("perfilDaoImpl")
	PerfilDao perfilDao;
	
	@Override
	public DefaultResponse logarPerfil(Perfil perfil, HttpSession session) {
		DefaultResponse response = new DefaultResponse();
		try{
			if(perfil != null){
				String nomeOuEmail = perfil.getNome();
				Perfil encontrado = perfilDao.pegarPorNome(nomeOuEmail);
				if(encontrado == null)
					encontrado = perfilDao.pegarPorEmail(nomeOuEmail);
				
				if(encontrado != null){
					String senha = perfil.getSenha();
					if(senha.equals(encontrado.getSenha())){
						session.setAttribute("logado", encontrado);
						Logs.info("[PerfilServiceImpl]::logarPerfil::Usuario logado");
						response.setStatus(Status.SUCESSO);
						response.setMensagem("Seja bem vindo, "+perfil.getNome());
						return response;
					}
					else{
						Logs.warn("[PerfilServiceImpl]::logarPerfil::Usuario ou senha incorretos: "+perfil.toString());
						response.setStatus(Status.AVISO);
						response.setMensagem("Usu�rio e/ou senha incorretos.");
						return response;
					}
				}
				else{
					Logs.warn("[PerfilServiceImpl]::logarPerfil::Usuario ou senha incorretos: "+perfil.toString());
					response.setStatus(Status.AVISO);
					response.setMensagem("Usu�rio e/ou senha incorretos.");
					return response;
				}
			}
			else{
				Logs.warn("[PerfilServiceImpl]::logarPerfil::objeto perfil nulo.");
				response.setStatus(Status.AVISO);
				response.setMensagem("Usu�rio e/ou senha nulos.");
				return response;
			}
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::logarPerfil::Falha fatal ao tentar logar perfil. Excecao:");
			e.printStackTrace();
			return response;
		}
	}

	@Override
	public DefaultResponse adicionarPerfil(Perfil perfil) {
		DefaultResponse response = new DefaultResponse();
		try{
			if(perfil != null){
				String nome = perfil.getNome();
				String email = perfil.getEmail();
				String senha = perfil.getSenha();
				if(!"".equals(nome) && !"".equals(email) && !"".equals(senha)){
					if(jaTemComEsteNome(nome)){
						Logs.warn("[PerfilServiceImpl]::adicionarPerfil:::Erro ao tentar adicionar perfil: ja tem cadastrado.");
						response.setStatus(Status.AVISO);
						response.setMensagem("J� existe um perfil com este nome.");
					}
					else{
						if(jaTemComEsteEmail(email)){
							Logs.warn("[PerfilServiceImpl]::adicionarPerfil:::Erro ao tentar adicionar perfil: ja tem cadastrado.");
							response.setStatus(Status.AVISO);
							response.setMensagem("J� existe um perfil com este email.");
						}
						else{
							perfil.setAtivo(true);
							if(perfil.getFoto() == null || perfil.getFoto().length < 5)
								perfil.setDefaultFoto();
							perfilDao.adicionar(perfil);
							Logs.warn("[PerfilServiceImpl]::adicionarPerfil:::Perfil [" + perfil.getNome() + "] cadastrado com exito.");
							response.setStatus(Status.SUCESSO);
							response.setMensagem("Perfil cadastrado!");
						}
					}
				}
				else{
					Logs.warn("[PerfilServiceImpl]::adicionarPerfil:::Erro ao tentar adicionar perfil. NULO");
					response.setStatus(Status.AVISO);
					response.setMensagem("Perfil com nome ou senha inv�lidos.");
				}
			}
			else{
				Logs.warn("[PerfilServiceImpl]::adicionarPerfil:::Erro ao tentar adicionar perfil. NULO");
				response.setStatus(Status.AVISO);
				response.setMensagem("Perfil com nome ou senha inv�lidos.");
			}
			
			return response;
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::adicionarPerfil:::Erro ao tentar adicionar perfil. Excecao:");
			e.printStackTrace();
			return response;
		}
	}

	@Override
	public DefaultResponse atualizarPerfil(Perfil perfil) {
		DefaultResponse response = new DefaultResponse();
		try{
			if(perfil != null){
				long id = perfil.getId();
				String nome = perfil.getNome();
				String email = perfil.getEmail();
				String senha = perfil.getSenha();
				if(!"".equals(nome) && !"".equals(email) && !"".equals(senha)){
					Perfil conflito = perfilDao.pegarPorNome(nome);
					if(conflito != null && conflito.getId() != id){
						Logs.warn("[PerfilServiceImpl]::atualizarPerfil:::Erro ao tentar atualizar perfil: ja tem cadastrado.");
						response.setStatus(Status.AVISO);
						response.setMensagem("J� existe um perfil com este nome.");
					}
					else{
						conflito = perfilDao.pegarPorEmail(email);
						if(conflito != null && conflito.getId() != id){
							Logs.warn("[PerfilServiceImpl]::atualizarPerfil:::Erro ao tentar atualizar perfil: ja tem cadastrado.");
							response.setStatus(Status.AVISO);
							response.setMensagem("J� existe um perfil com este email.");
						}
						else{
							Logs.info("[PerfilServiceImpl]::atualizarPerfil:::Perfil a ser atualizado: "+perfil.toString());
							perfilDao.atualizar(perfil);
							Logs.info("[PerfilServiceImpl]::atualizarPerfil:::Perfil [" + perfil.getNome() + "] atualizado com exito.");
							response.setStatus(Status.SUCESSO);
							response.setMensagem("Perfil atualizado!");
						}
					}
				}
				else{
					Logs.warn("[PerfilServiceImpl]::atualizarPerfil:::Erro ao tentar atualizar perfil. nome e/ou senha nulos");
					response.setStatus(Status.AVISO);
					response.setMensagem("Perfil com nome ou senha inv�lidos.");
				}
			}
			else{
				Logs.warn("[PerfilServiceImpl]::atualizarPerfil:::Erro ao tentar atualizar perfil. nulo");
				response.setStatus(Status.AVISO);
				response.setMensagem("Perfil com nome ou senha inv�lidos.");
			}
			
			return response;
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::atualizarPerfil:::Erro ao tentar atualizar perfil. Excecao:");
			e.printStackTrace();
			return response;
		}
	}

	@Override
	public DefaultResponse atualizarStatusDoPerfil(long id){
		DefaultResponse response = new DefaultResponse();
		
		try{
			Perfil perfil = perfilDao.pegarPorId(id);
			if(perfil.isAdmin()){
				int admins = perfilDao.contarPerfisAdministradores();
				if(admins < 2 && perfil.isAtivo()){
					Logs.warn("[PerfilServiceImpl]::atualizarStatusDoPerfil::Erro - Deve haver ao menos um perfil administrador cadastrado.");
					response.setStatus(Status.AVISO);
					response.setMensagem("Deve haver ao menos um perfil administrador cadastrado.");
				}
				else{
					perfil.setAtivo(!perfil.getAtivo());
					perfilDao.atualizar(perfil);
					Logs.info("[PerfilServiceImpl]::atualizarStatusDoPerfil::Perfil atualizado com exito: "+perfil.toString());
					response.setStatus(Status.SUCESSO);
					response.setMensagem("Perfil atualizado com exito.");
				}
			}
			else{
				perfil.setAtivo(!perfil.getAtivo());
				perfilDao.atualizar(perfil);
				Logs.info("[PerfilServiceImpl]::atualizarStatusDoPerfil::Perfil atualizado com exito: "+perfil.toString());
				response.setStatus(Status.SUCESSO);
				response.setMensagem("Perfil atualizado com exito.");
			}
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::atualizarStatusDoPerfil::Erro ao tentar atualizar o status do perfil. Exception:");
			e.printStackTrace();
			response.setStatus(Status.ERRO);
			response.setMensagem("Erro ao tentar atualizar perfil no servidor.");
		}
		
		return response;
	}
	
	@Override
	public DefaultResponse removerPerfil(long id) {
		DefaultResponse response = new DefaultResponse();
		try{
			List<Perfil> perfis = listarPerfis();
			if(perfis != null && perfis.size() > 1){
				Perfil perfil = perfilDao.pegarPorId(id);
				if(perfilDao.contarPerfisAdministradores() < 2 && perfil.isAdmin()){
					response.setStatus(Status.AVISO);
					response.setMensagem("Deve haver ao menos um perfil Administrador do sistema.");
					
					Logs.info("[PerfilServiceImpl]::removerPerfil::perfil nao removido. Deve haver ao menos um administrador do sistema.");
				}
				else{
					perfilDao.remover(perfil);
					response.setStatus(Status.SUCESSO);
					response.setMensagem("Perfil removido com sucesso.");
					
					Logs.info("[PerfilServiceImpl]::removerPerfil::perfil removido.");
				}
			}
			else{
				response.setStatus(Status.AVISO);
				response.setMensagem("Deve haver ao menos um perfil Administrador do sistema.");
				
				Logs.info("[PerfilServiceImpl]::removerPerfil::perfil nao removido. Deve haver ao menos um administrador do sistema.");
			}
			
			return response;
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::removerPerfil::Erro ao tentar remover perfil. Excecao:");
			e.printStackTrace();
			return response;
		}
	}

	@Override
	public List<Perfil> listarPerfis() {
		try{
			return perfilDao.listar();
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::listarPerfis:::Erro ao tentar listar perfis. Excecao:");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean init(){
		try{
			List<Perfil> perfis = listarPerfis();
			if(perfis == null || perfis.size() < 1){
				String nome = "admin";
				String email = "admin@exemplo.com.br";
				String senha="admin123";
				
				Perfil perfil = new Perfil();
				perfil.setNome(nome);
				perfil.setSenha(senha);
				perfil.setEmail(email);
				perfil.setDefaultFoto();
				perfil.setAdmin(true);
				perfil.setAtivo(true);
				
				adicionarPerfil(perfil);
				Logs.info("[PerfilServiceImpl]::init::Criacao de perfil padrao com exito. login: " + nome + " senha:"+senha);
				return true;
			}
			else{
				Logs.info("[PerfilServiceImpl]::init::Ja existe usuario cadastrado no banco de dados. Usuario padrao desnecessario.");
				return true;
			}
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::primeiroAcesso:::Erro ao tentar adicionar perfil. Excecao:");
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean jaTemComEsteNome(String nome){
		try{
			Perfil encontrado = perfilDao.pegarPorNome(nome);
			if(encontrado != null){
				Logs.info("[PerfilServiceImpl]::jaTemComEsteNome:::Ja existe perfil cadastrado com este nome [" + nome + "]");
				return true;
			}
			else{
				Logs.info("[PerfilServiceImpl]::jaTemComEsteNome:::Nao existe perfil cadastrado com este nome [" + nome + "]");
				return false;
			}
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::jaTemComEsteNome:::Erro ao tentar verificar se ja existe perfil com este nome [" + nome + "]. Excecao:");
			e.printStackTrace();
			return true;
		}
	}
	
	private boolean jaTemComEsteEmail(String email){
		try{
			Perfil encontrado = perfilDao.pegarPorEmail(email);
			if(encontrado != null){
				Logs.info("[PerfilServiceImpl]::jaTemComEsteEmail:::Ja existe perfil cadastrado com este email [" + email + "]");
				return true;
			}
			else{
				Logs.info("[PerfilServiceImpl]::jaTemComEsteEmail:::Nao existe perfil cadastrado com este email [" + email + "]");
				return false;
			}
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::jaTemComEsteEmail:::Erro ao tentar verificar se ja existe perfil com este email [" + email + "]. Excecao:");
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public Perfil pegarPorId(long id) {
		try{
			return perfilDao.pegarPorId(id);
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::pegarPorId:::Erro ao tentar Obter perfil por id. Excecao:");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Perfil pegarPorEmail(String email) {
		try{
			return perfilDao.pegarPorEmail(email);
		}
		catch(Exception e){
			Logs.warn("[PerfilServiceImpl]::pegarPorEmail:::Erro ao tentar Obter perfil por email. Excecao:");
			e.printStackTrace();
			return null;
		}
	}
	
}
