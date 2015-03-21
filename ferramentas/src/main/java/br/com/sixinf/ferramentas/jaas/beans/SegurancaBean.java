/**
 * 
 */
package br.com.sixinf.ferramentas.jaas.beans;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.sixinf.ferramentas.jaas.UserPrincipal;
import br.com.sixinf.ferramentas.log.LoggerException;
import br.com.sixinf.ferramentas.seguranca.persistencia.UsuarioSeguranca;

/**
 * @author maicon
 *
 */
public class SegurancaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UsuarioSeguranca usuario;

	public UsuarioSeguranca getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSeguranca usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * 
	 * @return
	 */
	private HttpServletRequest getRequest() {  
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
    }
	
	/**
	 * 
	 * @return
	 */
	private FacesContext getFacesContext() {  
        FacesContext context = FacesContext.getCurrentInstance();  
        return context;  
    } 
	
	/**
	 * @throws ServletException 
	 * @throws LoggerException 
	 * 
	 */
	public void autenticarUsuario() throws ServletException {
		if (getFacesContext().getExternalContext().getUserPrincipal() == null)
			getRequest().login(usuario.getNomeUsuario(), usuario.getSenhaSHA2());
		
		UserPrincipal up = (UserPrincipal) getFacesContext().getExternalContext().getUserPrincipal();
		this.usuario = up.getUsuario();
	}
	
	/**
	 * 
	 * @return
	 */
	public void logout(){
		try {  
            getRequest().logout();
            
            usuario = new UsuarioSeguranca();
              
        } catch (ServletException e) {  
            Logger.getLogger(getClass()).error("Erro no logout", e);
        }
	}
}
