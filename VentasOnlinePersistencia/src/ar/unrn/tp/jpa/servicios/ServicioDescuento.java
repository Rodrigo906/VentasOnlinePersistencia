package ar.unrn.tp.jpa.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.modelo.GestorPromociones;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.Promocion;
import ar.unrn.tp.modelo.PromocionDeCompra;
import ar.unrn.tp.modelo.PromocionDeProducto;

public class ServicioDescuento implements DescuentoService{

	private EntityManagerFactory emf;
	
	public ServicioDescuento(String emf) {
		this.emf = Persistence.createEntityManagerFactory(emf);
	}
	
	@Override
	public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta,
			float porcentaje) {
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
	
	public List<Promocion> listarDescuentos() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Promocion> promociones = new ArrayList<Promocion>();
		try {
			tx.begin();
			
			TypedQuery<Promocion> q = em.createQuery("select p from Promocion p", Promocion.class);
			promociones = q.getResultList();

			tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}
		return promociones;
	}

}
