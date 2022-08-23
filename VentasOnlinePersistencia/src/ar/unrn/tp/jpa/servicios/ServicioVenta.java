package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.Venta;

public class ServicioVenta implements VentaService{

	@Override
	public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			//Los productos en la venta los tengo que cambiar a una coleccion de ids de productos
			/*
			 * Crear el gestor de promociones
			 * Recuperar el cliente
			 * Crear el carrito
			 * Cargarle los productos
			 * Realizar venta
			 */
			
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
	public float calcularMonto(List<Long> productos, Long idTarjeta) {
		
		return 0;
	}

	@Override
	public List<Venta> ventas() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Venta> ventas  = new ArrayList<Venta>();
		try {
			tx.begin();
			
			TypedQuery<Venta> q = em.createQuery("select v from Venta v", Venta.class);
			ventas = q.getResultList();
			
			tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}
		return ventas;
	}

}
