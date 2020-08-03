package br.com.portalPeladas.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.portalPeladas.model.Pelada;
import br.com.portalPeladas.model.PeladaUsuario;
import br.com.portalPeladas.model.Usuario;

public class PeladaDAO {
	   private EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
	   private EntityManager em = factory.createEntityManager();
	    
	    
	    public List<Pelada> getPeladasUsuario(Usuario usuario) {

	        try {
	        	@SuppressWarnings("unchecked")
				List<Pelada> peladas = em
	                    .createQuery(
	                            "SELECT peladaUsuario.pelada from PeladaUsuario as peladaUsuario " + 
	                            "join peladaUsuario.pelada as pelada " + 
	                            "join peladaUsuario.usuario as usuario " + 
	                            "where usuario.usuarioId = :usuarioId ")
	                    .setParameter("usuarioId", usuario.getUsuarioId())
	                    .getResultList();

	            return peladas;
	        } catch (NoResultException e) {
	            return null;
	        }
	   
	    }

	    public boolean inserirPelada(Pelada pelada) {
	        try {
	        	Query query = em.createNativeQuery(
	                    "insert into Pelada (nomeEventoEsportivo, dataEvento, hora, localEvento) "
	                    + "values ( ?, ?, ?, ?)")
	            .setParameter(1, pelada.getNomeEventoEsportivo())
	            .setParameter(2, pelada.getDataEvento())
	            .setParameter(3, pelada.getHora())
	            .setParameter(4, pelada.getLocalEvento());
	        	
	        	em.getTransaction().begin();
	        	query.executeUpdate(); 
	        	em.getTransaction().commit();
	        	
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public boolean deletarPelada(Pelada pelada) {
	        try {
	            em.remove(pelada);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public List<Pelada> getAllPeladas() {

	        try {
	        	List<Pelada> peladas = em
	                    .createQuery(
	                            "SELECT p from Pelada p").getResultList();

	            return peladas;
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
	    
	    public Pelada getPelada(int peladaId) {

	        try {
	        	Pelada pelada =  (Pelada) em
	                    .createQuery(
	                            "SELECT p from Pelada p where p.peladaId = :peladaId")
	                    .setParameter("peladaId", peladaId)
	                    .getSingleResult();

	            return pelada;
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
	    
	    public Pelada getPeladaUsuario(int peladaId, int usuarioId) {

	        try {
	        	
	        	Pelada pelada = (Pelada) em.createQuery(
	                            "SELECT peladaUsuario.pelada from PeladaUsuario as peladaUsuario "
	                            + "join peladaUsuario.pelada as pelada "
	                            + "join peladaUsuario.usuario as usuario "
	                            + "where pelada.peladaId = :peladaId and usuario.usuarioId = :usuarioId")
	                    .setParameter("peladaId", peladaId)
	                    .setParameter("usuarioId", usuarioId)
	                    .getSingleResult();

	            return pelada;
	        } catch (NoResultException e) {
	            return null;
	        }
	    }

	    public boolean vinculaUsuarioPelada(int peladaId, int usuarioId ) {
	        try {
	            //em.persist(usuario);
	        	Query query = em.createNativeQuery(
	                    "insert into PeladaUsuario (peladaId, usuarioId) "
	                    + "values ( ?, ?)")
	            .setParameter(1, peladaId)
	            .setParameter(2, usuarioId);
	        	
	        	em.getTransaction().begin();
	        	query.executeUpdate(); 
	        	em.getTransaction().commit();

	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
}

