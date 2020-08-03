package br.com.portalPeladas.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.portalPeladas.model.Pelada;
import br.com.portalPeladas.model.Usuario;

public class UsuarioDAO {

    private EntityManagerFactory factory;
    private EntityManager em;
    
    public UsuarioDAO() {
    	if(factory == null) {
    		factory = Persistence
    	    		.createEntityManagerFactory("usuarios");
    		em = factory.createEntityManager();
    	}
    }
    
    
    public Usuario getUsuario(String email, String senha) {

        try {
            Usuario usuario = (Usuario) em
                    .createQuery(
                            "SELECT u from Usuario u where u.email = :email and u.senha = :senha")
                    .setParameter("email", email)
                    .setParameter("senha", senha).getSingleResult();

            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean inserirUsuario(Usuario usuario) {
        try {
            //em.persist(usuario);
        	Query query = em.createNativeQuery(
                    "insert into Usuario (nomeCompleto, apelido, email, senha) "
                    + "values ( ?, ?, ?, ?)")
            .setParameter(1, usuario.getNomeCompleto())
            .setParameter(2, usuario.getApelido())
            .setParameter(3, usuario.getEmail())
            .setParameter(4, usuario.getSenha());
        	
        	em.getTransaction().begin();
        	query.executeUpdate(); 
        	em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletarUsuario(Usuario usuario) {
        try {
            em.remove(usuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Usuario> getAllUsuarios() {

        try {
        	List<Usuario> usuarios = em
                    .createQuery(
                            "SELECT u from Usuario u").getResultList();

            return usuarios;
        } catch (NoResultException e) {
            return null;
        }
    }

}