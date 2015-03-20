/**
 * 
 */
package br.com.sixinf.ferramentas.jaas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.log4j.Logger;

import br.com.sixinf.ferramentas.jaas.dao.SegurancaDAO;
import br.com.sixinf.ferramentas.persistencia.Entidade;
import br.com.sixinf.ferramentas.seguranca.persistencia.UsuarioSeguranca;


/**
 * @author maicon
 *
 */
public class JaasLoginModule implements LoginModule {
	
	private Subject subject;
	private CallbackHandler handler;
	private UserPrincipal userPrincipal;
	private RolePrincipal rolePrincipal;
	private UsuarioSeguranca usuario;
	private List<String> userGroups;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.handler = callbackHandler;
	}
	
	
	@Override
	public boolean login() throws LoginException {
		Callback[] callbacks = new Callback[2];
	    callbacks[0] = new NameCallback("login");
	    callbacks[1] = new PasswordCallback("password", true);
		
	    try {
	    	
	    	handler.handle(callbacks);
	    	
	    	String nomeUsuario = ((NameCallback) callbacks[0]).getName();
	        String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
	        
	        UsuarioSeguranca u = SegurancaDAO.getInstance().buscarUsuario(nomeUsuario);
			
			if (u == null) {
				FacesMessage m = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Usuário não cadastrado", "Usuário não cadastrado");
				FacesContext.getCurrentInstance().addMessage(null, m);
				
				throw new LoginException("Usuário não cadastrado");
			}
			
			if (!u.getStatus().equals(Entidade.StatusRegistro.A)) {
				FacesMessage m = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Usuário desativado, acesso negado", "Usuário desativado, acesso negado");
				FacesContext.getCurrentInstance().addMessage(null, m);
				
				throw new LoginException("Usuário desativado, acesso negado");
			}
			
			if (!password.equals(u.getSenha())) {
				
				FacesMessage m = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Senha inválida", "Senha inválida");
				FacesContext.getCurrentInstance().addMessage(null, m);
				
				throw new LoginException("Senha inválida");
			} else {
				usuario = u;
				u.setSenha(null);
				userGroups = new ArrayList<String>();
				userGroups.add("user");
			}	    	
	    	
	    } catch (IOException e) {
	    	Logger.getLogger(getClass()).error("Erro no método de autenticação JAAS", e);
	    } catch (UnsupportedCallbackException e) {
	    	Logger.getLogger(getClass()).error("Erro no método de autenticação JAAS", e);
		}
	    
		return true;
	}

	
	@Override
	public boolean commit() throws LoginException {
		userPrincipal = new UserPrincipal(usuario);
	    subject.getPrincipals().add(userPrincipal);

	    if (userGroups != null && userGroups.size() > 0) {
	      for (String groupName : userGroups) {
	        rolePrincipal = new RolePrincipal(groupName);
	        subject.getPrincipals().add(rolePrincipal);
	      }
	    }

	    return true;
	}

	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#abort()
	 */
	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#logout()
	 */
	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
	    subject.getPrincipals().remove(rolePrincipal);
	    return true;
	}

}
