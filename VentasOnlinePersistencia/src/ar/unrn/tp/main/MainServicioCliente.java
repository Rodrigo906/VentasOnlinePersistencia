package ar.unrn.tp.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.jpa.servicios.ServicioCliente;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.TarjetaDeCredito;

public class MainServicioCliente {

	public static void main(String[] args) {
		
		//try {
			
		ClienteService sc = new ServicioCliente("ORM");
		//ClienteService sc = new ServicioCliente("$objectdb/db/p2.odb");
		//sc.crearCliente("Juan", "Lopez", "46467612", "juan@gmail.com");
		
		//sc.modificarCliente(4l, "Cristian", "41478211", "Garnica", "rodrigo111999@gmail.com");
		//sc.modificarCliente(29l, "Rodrigo", "42478211", "Calizaya", "rodrigo111999@gmail.com");
		
		//sc.agregarTarjeta(71l, "56782356", "Matercard");
		//sc.agregarTarjeta(1l, "1234567", "Visa");
	
		
		for (TarjetaDeCredito t: sc.listarTarjetas(1l)) {
			System.out.println("Tarjeta: "+ t.toString());
		}
		/*
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
		
	}
}
