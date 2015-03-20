/**
 * 
 */
package br.com.sixinf.ferramentas.seguranca.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sixinf.ferramentas.persistencia.Entidade;

/**
 * @author maicon.pasqualeto
 *
 */
@Entity
@Table(name="papel")
public class Papel implements Entidade, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seqPapel", sequenceName="papel_id_papel_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqPapel")
	@Column(name="id_papel")
	private Long idPapel;
	
	@Column(name="descricao")
	private String descricao;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="papel_acao", 
		joinColumns={
			@JoinColumn(name="idPapel")},
		inverseJoinColumns={
			@JoinColumn(name="idAcao")
		}
	)
	private List<Acao> acoes;
		
	@Override
	public Long getIdentificacao() {
		return getIdPapel();
	}

	public Long getIdPapel() {
		return idPapel;
	}

	public void setIdPapel(Long idPapel) {
		this.idPapel = idPapel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPapel == null) ? 0 : idPapel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Papel other = (Papel) obj;
		if (idPapel == null) {
			if (other.idPapel != null)
				return false;
		} else if (!idPapel.equals(other.idPapel))
			return false;
		return true;
	}
	
}
