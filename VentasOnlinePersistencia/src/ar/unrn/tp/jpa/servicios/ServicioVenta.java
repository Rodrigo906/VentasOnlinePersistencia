package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.CacheService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.Carrito;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.GestorPromociones;
import ar.unrn.tp.modelo.NextNumber;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.ProductoSeleccionado;
import ar.unrn.tp.modelo.TarjetaDeCredito;
import ar.unrn.tp.modelo.Venta;
import ar.unrn.tp.modelo.VerificarTarjeta;
import ar.unrn.tp.servicio_tarjeta.VerificacionDeTarjetaWeb;

public class ServicioVenta implements VentaService{

	private EntityManagerFactory emf;
	private CacheService serviceCache;

	public ServicioVenta(String emf, CacheService serviceCache) {
		this.emf = Persistence.createEntityManagerFactory(emf);
		this.serviceCache = serviceCache;
	}
	
	@Override
	public void realizarVenta(Long idCliente, Map<Long, Integer> productos, Long idTarjeta) {
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
			if (productos.isEmpty())
				throw new RuntimeException("No a ingresado productos");
				
			Carrito carrito = new Carrito(gestor, verificadorTarjeta, cliente);
			
			for(Map.Entry<Long, Integer> entry : productos.entrySet()) {
				Producto p = em.find(Producto.class, entry.getKey());
				carrito.agregarProducto(new ProductoSeleccionado(p, entry.getValue()));	
			}
			
			TypedQuery<NextNumber> qNumber = em.createQuery("select n from NextNumber n", NextNumber.class);
			qNumber.setLockMode(LockModeType.PESSIMISTIC_WRITE);
			NextNumber n = qNumber.getSingleResult();
			
			Venta venta = carrito.realizarVenta(tarjeta, n.recuperarSiguiente());
			em.persist(venta);
			this.serviceCache.registrarVenta(venta, idCliente);
				
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
	
	public List<Venta> ultimasTresVentas(long idCliente){
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Venta> ventas  = new ArrayList<Venta>();
		try {
			tx.begin();
			
			ventas = this.serviceCache.obtenerVentas(idCliente);
			if (ventas.isEmpty())
				{
					TypedQuery<Venta> q = em.createQuery("SELECT v FROM Venta v WHERE v.cliente.id = :idCliente ORDER BY v.fecha DESC", Venta.class)
							.setMaxResults(3);
					q.setParameter("idCliente", idCliente);
					
					ventas = q.getResultList();
					this.serviceCache.cargarVentas(ventas, idCliente);
				}
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
