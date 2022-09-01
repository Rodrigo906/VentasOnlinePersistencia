package ar.unrn.tp.jpa.servicios;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.modelo.GestorPromociones;
import ar.unrn.tp.modelo.PromocionDeCompra;
import ar.unrn.tp.modelo.PromocionDeProducto;

public class ServicioDescuento implements DescuentoService{

	@Override
	public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta,
			float porcentaje) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			GestorPromociones gestor = em.getReference(GestorPromociones.class, 8l);
			
			boolean seAgrego = gestor.agregarPromocionCompra(new PromocionDeCompra(fechaDesde, fechaHasta, marcaTarjeta, porcentaje));
			if(!seAgrego)
				throw new RuntimeException("No se pudo agregar la promocion");
					
			tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}
	}

	@Override
	public void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			GestorPromociones gestor = em.find(GestorPromociones.class, 8l);
			boolean seAgrego = gestor.agregarPromocionProducto(new PromocionDeProducto(fechaDesde, fechaHasta, marcaProducto, porcentaje));
			if(!seAgrego)
				throw new RuntimeException("No se pudo agregar la promocion");

			tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}
	}
	
	public void crearGestor() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
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
