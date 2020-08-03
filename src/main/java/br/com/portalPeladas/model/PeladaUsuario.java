package br.com.portalPeladas.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PeladaUsuario {
	    @Id
	    @Column(name="peladaUsuarioId", nullable=false, unique=true)
	    private int peladaUsuarioId;
	    
	    @ManyToOne(cascade={CascadeType.PERSIST})
	    @JoinColumn(name = "usuarioId") 
	    private Usuario usuario;
	    
	    @ManyToOne(cascade={CascadeType.PERSIST})
	    @JoinColumn(name = "peladaId") 
	    private Pelada pelada;

		public int getPeladaUsuarioId() {
			return peladaUsuarioId;
		}

		public void setPeladaUsuarioId(int peladaUsuarioId) {
			this.peladaUsuarioId = peladaUsuarioId;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public Pelada getPelada() {
			return pelada;
		}

		public void setPelada(Pelada pelada) {
			this.pelada = pelada;
		}
	    
}

