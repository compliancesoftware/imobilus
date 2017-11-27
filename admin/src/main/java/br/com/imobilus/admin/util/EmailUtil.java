package br.com.imobilus.admin.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.imobilus.admin.model.Mailing;

public class EmailUtil {
	private static Properties props;
	private static String username;
	private static String password;
	
	/**
	 * Envia o mesmo e-mail para todos da lista
	 * @param emails
	 */
	public static boolean enviar(String emails, String assunto, String corpo, List<Mailing> propriedades) throws Exception {
		boolean result = false;
		
		try {
			props = new Properties();
			
			for(Mailing propriedade : propriedades){
				props.put(propriedade.getKey(), propriedade.getValue());
			}
			
			username = props.getProperty("username");
			password = props.getProperty("password");

			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			session.setDebug(true);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			
			String lista = emails.replace("'", "").replace("[", "").replace("]", "");
			
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(lista));
			message.setSubject(assunto);
			message.setText(corpo);
			
			Transport.send(message);
			
			result = true;
		} catch (AddressException e) {
			throw new Exception ("E-mail informado inválido.");
		} catch (MessagingException e) {
			throw new Exception ("Erro ao tentar enviar e-mail: "+e.getLocalizedMessage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
