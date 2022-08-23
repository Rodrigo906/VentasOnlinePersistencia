package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Producto;

public class ServicioProducto implements ProductoService{

	@Override
	public void crearProducto(String codigo, String descripcion, float precio, String categoria, String marca) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			if(!this.productoExiste(codigo))
			{
				Producto p = new Producto(codigo, descripcion, categoria, marca, precio);
				em.persist(p);
			}
			else
				throw new RuntimeException("El producto ya se encuentra registrado");
			
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
	public void modificarProducto(Long idProducto, String codigo, String descripcion, float precio, String categoria, String marca) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			if(this.productoExiste(idProducto))
			{
				Producto p = em.getReference(Producto.class, idProducto);
				p.setCodigo(codigo);
				p.setDescripcion(descripcion);
				p.setPrecio(precio);
				p.setCategoria(categoria);
				p.setMarca(marca);
			}
			else
				throw new RuntimeException("El producto no se encuentra registrado");
			
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
	public List<Producto> listarProductos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Producto> productos  = new ArrayList<Producto>();
		try {
			tx.begin();
			
			TypedQuery<Producto> q = em.createQuery("select p from Producto p", Producto.class);
			productos = q.getResultList();
			
			tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}
		return productos;
	}

	private boolean productoExiste(long idProducto) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Producto> productos = new ArrayList<Producto>();
		boolean existe = false;
		try {
			tx.begin();
			
			TypedQuery<Producto> q = em.createQuery("select p from Producto p where p.id="+ idProducto, Producto.class);
			productos = q.getResultList();
			
			if(!productos.isEmpty()) {
				existe = true;
			}
			
			tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}
		return existe;
	}
	
	private boolean productoExiste(String codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Producto> productos = new ArrayList<Producto>();
		boolean existe = false;
		try {
			tx.begin();
			
			TypedQuery<Producto> q = em.createQuery("select p from Producto p where p.codigo="+ codigo, Producto.class);
			productos = q.getResultList();
			
			if(!productos.isEmpty()) {
				existe = true;
			}
			
			tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}
		return existe;
	}
}
