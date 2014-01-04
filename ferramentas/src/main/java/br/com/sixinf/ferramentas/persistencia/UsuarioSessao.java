/**
 * 
 */
package br.com.sixinf.ferramentas.persistencia;

import br.com.sixinf.ferramentas.seguranca.persistencia.Usuario;

/**
 * @author maicon.pasqualeto
 *
 */
public class UsuarioSessao {
	
	private static UsuarioSessao usuarioSessao;
	
	private Usuario usuario;
	
	public static UsuarioSessao getInstance(){
		if (usuarioSessao == null){
			usuarioSessao = new UsuarioSessao();
		}
		return usuarioSessao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
