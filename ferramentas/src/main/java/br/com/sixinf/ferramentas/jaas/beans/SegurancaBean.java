/**
 * 
 */
package br.com.sixinf.ferramentas.jaas.beans;

import java.io.Serializable;

import javax.faces.context.FacesContext;
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
	 * @throws LoggerException 
	 * 
	 */
	public void autenticarUsuario() throws LoggerException {
		try {
			
			if (getFacesContext().getExternalContext().getUserPrincipal() == null)
				getRequest().login(usuario.getNomeUsuario(), usuario.getSenha());
			
			UserPrincipal up = (UserPrincipal) getFacesContext().getExternalContext().getUserPrincipal();
			this.usuario = up.getUsuario();
						
		} catch (Exception e) {
			//Logger.getLogger(getClass()).error("Erro ao efetuar login jaas", e);
			throw new LoggerException("Erro ao efetuar login jaas", e, Logger.getLogger(getClass()));
		}		
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
