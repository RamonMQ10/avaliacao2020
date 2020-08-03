package br.com.portalPeladas.managedbeans;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.com.portalPeladas.db.PeladaDAO;
import br.com.portalPeladas.db.UsuarioDAO;
import br.com.portalPeladas.model.Pelada;
import br.com.portalPeladas.model.PeladaUsuario;
import br.com.portalPeladas.model.Usuario;

@ManagedBean(name = "PeladaMB")
@SessionScoped
public class PeladaManagedBean {

	private PeladaDAO peladaDAO = new PeladaDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();
	private Pelada pelada;
	private String mensagem;
	private int selectedPeladaId;
	private int selectedUsuarioId;
	private String userName;

	public String inicio() {
		pelada = new Pelada();
		return "/main";
	}

	public String cadastrarPelada() {
		pelada = new Pelada();
		return "/cadastrarPelada";
	}

	public String listarPelada() {
		pelada = new Pelada();
		return "/lstPelada";
	}

	public String sair() {
		pelada = new Pelada();
		return "/index";
	}

	public List<Pelada> getListaPeladasUsuario() {

		List<Pelada> peladas = new ArrayList<Pelada>();

		try {
			Usuario usuario = new Usuario();
			usuario.setUsuarioId(this.getSelectedUsuarioId());

			peladas = peladaDAO.getPeladasUsuario(usuario);

		} catch (Exception e) {
			mensagem = e.getMessage();
		}

		return peladas;
	}

	public List<Pelada> getListaPeladas() {

		List<Pelada> peladas = new ArrayList<Pelada>();

		try {
			peladas = peladaDAO.getAllPeladas();

		} catch (Exception e) {
			mensagem = e.getMessage();
		}

		return peladas;
	}

	public Pelada getPelada() {
		return pelada;
	}

	public void setPelada(Pelada pelada) {
		this.pelada = pelada;
	}

	public int getSelectedPeladaId() {
		return selectedPeladaId;
	}

	public void setSelectedPeladaId(int selectedPeladaId) {
		this.selectedPeladaId = selectedPeladaId;
	}

	public int getSelectedUsuarioId() {
		return selectedUsuarioId;
	}

	public void setSelectedUsuarioId(int selectedUsuarioId) {
		this.selectedUsuarioId = selectedUsuarioId;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String vinculaUsuarioPelada() {

		try {

			Pelada pelada = peladaDAO.getPeladaUsuario(this.getSelectedPeladaId(), this.getSelectedUsuarioId());

			if (pelada != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Usuário já está vinculado a pelada selecionada!", ""));
			} else {
				peladaDAO.vinculaUsuarioPelada(this.getSelectedPeladaId(), this.getSelectedUsuarioId());

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário vinculado a pelada selecionada!", ""));
			}

		} catch (Exception e) {
			mensagem = e.getMessage();
			System.out.println("ErroAA: " + mensagem);
		}

		return "/main";
	}

	public String salvarNovaPelada() {

		//pelada.setHora(this.formataData(pelada.getDataEvento(), pelada.getHora()));
		peladaDAO.inserirPelada(pelada);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Pelada Cadastrada com Sucesso!", ""));

		return "/main";

	}
	
	public String convidarUsuarioPelada() {
		
		pelada = peladaDAO.getPelada(this.getSelectedPeladaId());
		
		if (pelada == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro ao convidar amigo!", ""));
			
			return "/main";
		} else {
			return "/convidarUsuarioPelada";
		}
		
		
	}
	
	public List<Usuario> getListaUsuarios() {

		List<Usuario> listUsuarios = new ArrayList<Usuario>();

		try {
			listUsuarios = usuarioDAO.getAllUsuarios();

		} catch (Exception e) {
			mensagem = e.getMessage();
		}

		return listUsuarios;
	}

}
