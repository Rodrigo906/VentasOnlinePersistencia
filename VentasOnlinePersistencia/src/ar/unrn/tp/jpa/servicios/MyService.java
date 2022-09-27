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
import ar.unrn.tp.modelo.NextNumber;

public class MyService {
	
	private EntityManagerFactory emf;

	public MyService(String emf) {
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
	
	public void inicializarContadorCodigoVenta(int anio, int number) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			NextNumber n = new NextNumber(anio, number);
			em.persist(n);
			
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
