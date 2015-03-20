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
				
		A("ATIVO"),  
		D("INATIVO");
		
		private String descricao;
		
		private StatusRegistro(String descricao) {
			this.descricao = descricao;
		}
		
		public Character getStatus() {
			return this.name().charAt(0);
		}		
		
		public String getDescricao() {
			return this.descricao;
		}
	}
}
