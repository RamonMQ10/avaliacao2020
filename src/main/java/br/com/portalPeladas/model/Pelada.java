package br.com.portalPeladas.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pelada {

	@Id
	@Column(name = "peladaId", nullable = false, unique = true)
	private int peladaId;

	@Column(name = "nomeEventoEsportivo", nullable = false, unique = false)
	private String nomeEventoEsportivo;

	@Column(name = "dataEvento", nullable = false, unique = false)
	private Date dataEvento;

	@Column(name = "hora", nullable = false, unique = false)
	private Date hora;

	@Column(name = "localEvento", nullable = false, unique = false)
	private String localEvento;

	public int getPeladaId() {
		return peladaId;
	}

	public void setPeladaId(int peladaId) {
		this.peladaId = peladaId;
	}

	public String getNomeEventoEsportivo() {
		return nomeEventoEsportivo;
	}

	public void setNomeEventoEsportivo(String nomeEventoEsportivo) {
		this.nomeEventoEsportivo = nomeEventoEsportivo;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getLocalEvento() {
		return localEvento;
	}

	public void setLocalEvento(String localEvento) {
		this.localEvento = localEvento;
	}

}
