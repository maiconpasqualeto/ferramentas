/**
 * 
 */
package br.com.sixinf.ferramentas.jaas;

import java.security.Principal;

/**
 * @author maicon
 *
 */
public class RolePrincipal implements Principal {

	private String name;

	public RolePrincipal(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
