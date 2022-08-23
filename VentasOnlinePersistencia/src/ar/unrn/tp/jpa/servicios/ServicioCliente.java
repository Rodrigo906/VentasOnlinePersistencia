package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.TarjetaDeCredito;

public class ServicioCliente implements ClienteService{
	
	@Override
	public void crearCliente(String nombre, String apellido, String dni, String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			tx.begin();
			
			TypedQuery<Cliente> q = em.createQuery("select c from Cliente c where c.dni="+ dni, Cliente.class);
			clientes = q.getResultList();
			if (clientes.isEmpty())
			{
				Cliente cliente = new Cliente(Integer.valueOf(dni), nombre, apellido, email);
				em.persist(cliente);
			}
			else
				throw new RuntimeException("El dni del cliente ya se encuentra registrado");
			
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
	public void modificarCliente(Long idCliente, String nombre, String dni, String apellido, String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			if(this.clienteExiste(idCliente))
			{
				Cliente cliente = em.getReference(Cliente.class, idCliente);
				cliente.setNombre(nombre);
				cliente.setDni(Integer.valueOf(dni));
				cliente.setApellido(apellido);
				cliente.setEmail(email);
			}
			else
				throw new RuntimeException("El cliente no se encuentra registrado");
			
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
	public void agregarTarjeta(Long idCliente, String nro, String marca) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			if(this.clienteExiste(idCliente))
			{
				Cliente cliente = em.getReference(Cliente.class, idCliente);
				cliente.agregarTarjeta(new TarjetaDeCredito(Integer.valueOf(nro), marca));
			}
			else
				throw new RuntimeException("El cliente no se encuentra registrado");
			
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
	public List<TarjetaDeCredito> listarTarjetas(Long idCliente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<TarjetaDeCredito> tarjetas = null;
		try {
			tx.begin();
			
			Cliente cliente = em.getReference(Cliente.class, idCliente);
			tarjetas = cliente.obtenerTarjetas();
			
			tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RuntimeException(e);
			} finally {
				if (em != null && em.isOpen())
				 em.close();
			}
		return tarjetas;
	}
	
	private boolean clienteExiste(long idCliente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Cliente> clientes = new ArrayList<Cliente>();
		boolean existe = false;
		try {
			tx.begin();
			
			TypedQuery<Cliente> q = em.createQuery("select c from Cliente c where c.id="+ idCliente, Cliente.class);
			clientes = q.getResultList();
			
			if(!clientes.isEmpty()) {
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
