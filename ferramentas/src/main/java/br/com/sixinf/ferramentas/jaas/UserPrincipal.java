/**
 * 
 */
package br.com.sixinf.ferramentas.jaas;

import java.security.Principal;

import br.com.sixinf.ferramentas.seguranca.persistencia.UsuarioSeguranca;


/**
 * @author maicon
 *
 */
public class UserPrincipal implements Principal {

	private UsuarioSeguranca usuario;

	public UserPrincipal(UsuarioSeguranca usuario) {
		super();
		this.usuario = usuario;
	}
	
	public void setName(String nomeUsuario) {
		this.usuario.setNomeUsuario(nomeUsuario);
	}

	@Override
	public String getName() {
		return usuario.getNomeUsuario();
	}

	public UsuarioSeguranca getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSeguranca usuario) {
		this.usuario = usuario;
	}

}
