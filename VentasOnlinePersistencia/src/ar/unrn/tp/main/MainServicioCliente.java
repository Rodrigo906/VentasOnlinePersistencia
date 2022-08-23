package ar.unrn.tp.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import ar.unrn.tp.jpa.servicios.ServicioCliente;
import ar.unrn.tp.modelo.Cliente;

public class MainServicioCliente {

	public static void main(String[] args) {
		ServicioCliente sc = new ServicioCliente();
		sc.crearCliente("Pedro", "Calizaya", "41478211", "rodrigo111999@gmail.com");
	/*
		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
		emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		em = emf.createEntityManager();
		tx = em.getTransaction();
		}catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
		try {
			tx.begin();
			
			Cliente cliente = new Cliente(41478211, "Cristian", "Calizaya", "rodrigo111999@gmail.com");
			em.persist(cliente);
			TypedQuery<Cliente> q = em.createQuery("select p from Cliente p", Cliente.class);
					List<Cliente> personas = q.getResultList();
					for (Cliente persona : personas) {
					 System.out.println(persona.getNombre());
					}
			
			tx.commit();
			} catch (Exception e) {
				tx.rollback();
			throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}*/
	}
}
