package br.com.portalPeladas.managedbeans;

import br.com.portalPeladas.db.UsuarioDAO;
import br.com.portalPeladas.model.Usuario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "LoginMB")
@SessionScoped
public class LoginManagedBean {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuario = new Usuario();
    
    public String envia() {

        usuario = usuarioDAO.getUsuario(usuario.getEmail(), usuario.getSenha());
        if (usuario == null) {
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                            "Erro no Login!"));
            return null;
        } else {
            return "/main";
        }


    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String preparaCadastroUsuario() {
    	usuario = new Usuario();
        return "/cadastrarUsuario";
        
    }
    
    public String salvarNovoUsuario() {
    	
    	Usuario usuarioDb = usuarioDAO.getUsuario(usuario.getEmail(), usuario.getSenha());
        if (usuarioDb != null) {
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário Já Cadastrado!",
                            ""));
        } else {
        	usuarioDAO.inserirUsuario(usuario);
        	FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário Cadastrado com Sucesso!",
                            ""));
        }
        
        return "/index";
    	
    }
}
