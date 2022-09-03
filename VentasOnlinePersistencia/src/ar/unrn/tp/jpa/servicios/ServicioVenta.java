package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.Carrito;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.GestorPromociones;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.ProductoSeleccionado;
import ar.unrn.tp.modelo.TarjetaDeCredito;
import ar.unrn.tp.modelo.Venta;
import ar.unrn.tp.modelo.VerificarTarjeta;
import ar.unrn.tp.servicio_tarjeta.VerificacionDeTarjetaWeb;

public class ServicioVenta implements VentaService{

	@Override
	public void realizarVenta(Long idCliente, Map<Long, Integer> productos, Long idTarjeta) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		VerificarTarjeta verificadorTarjeta = new VerificacionDeTarjetaWeb();
		try {
			tx.begin();
			
			TypedQuery<GestorPromociones> q = em.createQuery("select g from GestorPromociones g", GestorPromociones.class);
			GestorPromociones gestor = q.getSingleResult();
			Cliente cliente = em.getReference(Cliente.class, idCliente);
			TarjetaDeCredito tarjeta =  em.getReference(TarjetaDeCredito.class, idTarjeta);
			
			if (!cliente.tieneEstaTarjeta(tarjeta))
				throw new RuntimeException("La tarjeta no se encuetra asociada a su usuario");
				
			Carrito carrito = new Carrito(gestor, verificadorTarjeta, cliente);
			
			for(Map.Entry<Long, Integer> entry : productos.entrySet()) {
				Producto p = em.find(Producto.class, entry.getKey());
				carrito.agregarProducto(new ProductoSeleccionado(p, entry.getValue()));	
			}
			Venta venta = carrito.realizarVenta(tarjeta);
			em.persist(venta);
			
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
	public float calcularMonto(Map<Long, Integer> productos, Long idTarjeta) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<ProductoSeleccionado> productosSeleccionados = new ArrayList<>();
		float montoTotal = 0;
		try {
			tx.begin();
			
			TypedQuery<GestorPromociones> q = em.createQuery("select g from GestorPromociones g", GestorPromociones.class);
			GestorPromociones gestor = q.getSingleResult();
			
			TarjetaDeCredito tarjeta =  em.getReference(TarjetaDeCredito.class, idTarjeta);
			
			for(Map.Entry<Long, Integer> entry : productos.entrySet()) {
				Producto p = em.getReference(Producto.class, entry.getKey());
				productosSeleccionados.add(new ProductoSeleccionado(p, entry.getValue()));
			}
			montoTotal = gestor.calcularMontoTotal(tarjeta.getMarca(), productosSeleccionados);
			
			tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}
		return montoTotal;
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
