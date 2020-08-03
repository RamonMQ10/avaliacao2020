package br.com.portalPeladas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {

    @Id
    @Column(name="usuarioId", nullable=false, unique=true)
    private int usuarioId;

    @Column(name="nomeCompleto", nullable=false, unique=false)
    private String nomeCompleto;
    
    @Column(name="apelido", nullable=false, unique=false)
    private String apelido;
    
    @Column(name="email", nullable=false, unique=false)
    private String email;
    
    @Column(name="senha", nullable=false, unique=false)
    private String senha;

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
