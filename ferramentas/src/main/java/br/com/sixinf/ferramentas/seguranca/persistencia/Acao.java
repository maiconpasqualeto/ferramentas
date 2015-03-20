/**
 * 
 */
package br.com.sixinf.ferramentas.seguranca.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sixinf.ferramentas.persistencia.Entidade;

/**
 * @author maicon.pasqualeto
 *
 */
@Entity
@Table(name="acao")
public class Acao implements Entidade, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="seqAcao", sequenceName="acao_id_acao_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqAcao")
	@Column(name="id_acao")
	private Long idAcao;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="sigla_acao")
	private String siglaAcao;
	
	@Column(name="tipo_acao")
	@Enumerated(EnumType.STRING)
	private TipoAcao tipoAcao;
	
	@Override
	public Long getIdentificacao() {
		return getIdAcao();
	}

	public Long getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Long idAcao) {
		this.idAcao = idAcao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoAcao getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(TipoAcao tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public String getSiglaAcao() {
		return siglaAcao;
	}

	public void setSiglaAcao(String siglaAcao) {
		this.siglaAcao = siglaAcao;
	}

}
