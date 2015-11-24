/**
 * 
 */
package br.com.sixinf.ferramentas.email;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.apache.log4j.Logger;

/**
 * @author maicon
 *
 */
public class EmailUtils {
	
	private String fromHostName; // "email-ssl.com.br" // "smtp.zoho.com"
	private String fromEmail; // "webmaster@lordpetstore.com.br" // "maicon@sixinf.com.br"
	private String fromEmailName; // "Ecommerce Lord Pet Store" // "Lord Pet Store"
	private String fromUser; // "webmaster@lordpetstore.com.br" // "maicon@sixinf.com.br"
	private String fromPass; // "mariana123!@"                  // "mariana123!@"
	
	public EmailUtils(String fromHostName, String fromEmail,
			String fromEmailName, String fromUser, String fromPass) {
		super();
		this.fromHostName = fromHostName;
		this.fromEmail = fromEmail;
		this.fromEmailName = fromEmailName;
		this.fromUser = fromUser;
		this.fromPass = fromPass;
	}

	public String getFromHostName() {
		return fromHostName;
	}

	public void setFromHostName(String fromHostName) {
		this.fromHostName = fromHostName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromEmailName() {
		return fromEmailName;
	}

	public void setFromEmailName(String fromEmailName) {
		this.fromEmailName = fromEmailName;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getFromPass() {
		return fromPass;
	}

	public void setFromPass(String fromPass) {
		this.fromPass = fromPass;
	}

	/**
	 * 
	 * @param nome
	 * @param enderecoEmail
	 * @param assunto
	 * @param mensagem
	 */
	public void enviarEmail(final String nome, final String enderecoEmail, 
			final String assunto, final String mensagem) {
		enviarEmail(nome, enderecoEmail, assunto, mensagem, false, null, null);
	}
	
	/**
	 * 
	 * @param nome
	 * @param enderecoEmail
	 * @param assunto
	 * @param mensagem
	 * @param formatoHtml
	 * @param mensagemHtmlAlternativa
	 * @param urlContentResolver
	 */
	public void enviarEmail(final String nome, final String enderecoEmail, 
				final String assunto, final String mensagem, final boolean formatoHtml, 
				final String mensagemHtmlAlternativa, final String urlContentResolver) {
		
		new Thread(new Runnable() {			
			
			@Override
			public void run() {
				try {
					Email em = null;  		
					if (formatoHtml) {
						ImageHtmlEmail email = new ImageHtmlEmail();
						email.setHtmlMsg(mensagem);
						email.setTextMsg(mensagemHtmlAlternativa);
						if (urlContentResolver != null) 
							email.setDataSourceResolver(new DataSourceUrlResolver(new URL(urlContentResolver)));
						em = email;
					} else {
						SimpleEmail email = new SimpleEmail();
						email.setMsg(mensagem); //conteudo do e-mail
						em = email;
					}
			        em.setHostName(fromHostName); // o servidor SMTP para envio do e-mail  
			        em.addTo(enderecoEmail, nome); //destinatário  
			        em.setFrom("webmaster@lordpetstore.com.br", "Ecommerce Lord Pet Store"); // remetente  
			        em.setSubject(assunto); // assunto do e-mail  
			        
			        em.setAuthentication("webmaster@lordpetstore.com.br", "mariana123!@");  
			        em.setSmtpPort(465);  
			        em.setSSLOnConnect(true);  
			        em.setStartTLSEnabled(true);  
			        em.send();
			        
			        Logger.getLogger(getClass()).debug(
			        		"Email enviado para: " + "\r\n" + 
			        		nome + "\r\n" + 
			        		enderecoEmail + "\r\n" +
			        		assunto + "\r\n" + 
			        		mensagem);
					
				} catch (EmailException e) {
					Logger.getLogger(getClass()).error("Erro ao enviar email", e);
				} catch (MalformedURLException e) {
					Logger.getLogger(getClass()).error("Erro ao enviar email", e);
				}
		        
			}
		}, "Thread-Envia-Email" ).start();
	}

}
