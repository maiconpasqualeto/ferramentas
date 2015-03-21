package br.com.sixinf.ferramentas.seguranca.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sixinf.ferramentas.Utilitarios;
import br.com.sixinf.ferramentas.persistencia.Entidade;

@Entity
@Table(name="usuario_seguranca")
public class UsuarioSeguranca implements Entidade, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seqUsuarioSeguranca", 
		sequenceName="usuario_seguranca_id_usuario_seguranca_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqUsuarioSeguranca")
	@Column(name="id_usuario_seguranca")
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
	
	@Column(name="status_registro")
	@Enumerated(value=EnumType.STRING)
	private Entidade.StatusRegistro status;
	
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

	public Entidade.StatusRegistro getStatus() {
		return status;
	}

	public void setStatus(Entidade.StatusRegistro status) {
		this.status = status;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSenhaSHA2() {
		if (senha != null) 
			return Utilitarios.geraHashSHA2(senha);
		return "";
	}
	
}
