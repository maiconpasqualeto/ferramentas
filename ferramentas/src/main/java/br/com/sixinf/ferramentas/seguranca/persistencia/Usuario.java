package br.com.sixinf.ferramentas.seguranca.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sixinf.ferramentas.persistencia.Entidade;

@Entity
@Table(name="usuario")
public class Usuario implements Entidade, Serializable {
	
	@Id
	@SequenceGenerator(name="seqUsuario", sequenceName="usuario_id_usuario_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqUsuario")
	@Column(name="id_usuario")
	private Long idUsuario;
	
	@Column(name="nome_usuario")
	private String nomeUsuario;
	
	@Column(name="nome_completo")
	private String nomeCompleto;
	
	@ManyToOne(targetEntity=Papel.class)
	@JoinColumn(name="id_papel")
	private Papel pepel;
	
	@Column(name="senha")
	private String senha;
	
	@Override
	public Long getIdentificacao() {
		return getIdUsuario();
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Papel getPepel() {
		return pepel;
	}

	public void setPepel(Papel pepel) {
		this.pepel = pepel;
	}

		
}
