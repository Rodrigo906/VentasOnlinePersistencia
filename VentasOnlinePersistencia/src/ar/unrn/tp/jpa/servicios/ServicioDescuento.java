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
			
			PromocionDeCompra pc = new PromocionDeCompra(fechaDesde, fechaHasta, marcaTarjeta, porcentaje);
			em.persist(pc);

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
			
			PromocionDeProducto pp = new PromocionDeProducto(fechaDesde, fechaHasta, marcaProducto, porcentaje);
			em.persist(pp);

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
