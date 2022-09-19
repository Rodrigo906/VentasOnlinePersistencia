package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.GestorPromociones;

public class ServicioGestorPromociones {
	
	private EntityManagerFactory emf;

	public ServicioGestorPromociones(String emf) {
		this.emf = Persistence.createEntityManagerFactory(emf);
	}
	
	public void crearGestor() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
		
			GestorPromociones gestor = new GestorPromociones();
			em.persist(gestor);
			
			tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}
	}
}
