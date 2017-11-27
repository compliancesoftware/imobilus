package br.com.imobilus.admin.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gera logs no console mais facilmente.
 * S�o encontrados no arquivo catalina*.log dentro da pasta de logs do tomcat
 * @author douglas.f.filho
 *
 */
public class Logs{
	
	/**
	 * Loga uma informa��o do sistema.
	 * @param mensagem
	 */
	public static void info(String mensagem){
		Logger logger = Logger.getLogger("info");
		logger.log(Level.INFO, mensagem);
	}
	
	/**
	 * Loga um alerta do sistema.
	 * @param mensagem
	 */
	public static void warn(String mensagem){
		Logger logger = Logger.getLogger("warn");
		logger.log(Level.WARNING, mensagem);
	}
	
	/**
	 * Loga uma falha do sistema.
	 * @param mensagem
	 */
	public static void debug(String mensagem){
		Logger logger = Logger.getLogger("debug");
		logger.log(Level.CONFIG, mensagem);
	}
}