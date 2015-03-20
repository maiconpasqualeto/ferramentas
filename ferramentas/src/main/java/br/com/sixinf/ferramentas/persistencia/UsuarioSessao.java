/**
 * 
 */
package br.com.sixinf.ferramentas.persistencia;

import br.com.sixinf.ferramentas.seguranca.persistencia.UsuarioSeguranca;

/**
 * @author maicon.pasqualeto
 *
 */
public class UsuarioSessao {
	
	private static UsuarioSessao usuarioSessao;
	
	private UsuarioSeguranca usuario;
	
	public static UsuarioSessao getInstance(){
		if (usuarioSessao == null){
			usuarioSessao = new UsuarioSessao();
		}
		return usuarioSessao;
	}

	public UsuarioSeguranca getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSeguranca usuario) {
		this.usuario = usuario;
	}
	
	

}
