/**
 * 
 */
package br.com.sixinf.ferramentas.persistencia;

/**
 * @author maicon.pasqualeto
 *
 */
public interface Entidade {
	
	public Long getIdentificacao();
	
	
	
	public enum StatusRegistro {
				
		ATIVO('A'),  
		INATIVO('D');
		
		private final char status;
		
		private StatusRegistro(char status) {
			this.status = status;
		}
		
		public char getStatus() {
			return status;
		}		
		
	}
}
