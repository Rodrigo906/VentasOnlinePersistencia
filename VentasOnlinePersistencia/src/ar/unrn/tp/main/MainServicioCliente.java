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
		
		try {
			
		ClienteService sc = new ServicioCliente("$objectdb/db/p2.odb");
		//sc.crearCliente("Cristian", "Garnica", "42478211", "rodrigo111999@gmail.com");
		
		//sc.modificarCliente(4l, "Cristian", "41478211", "Garnica", "rodrigo111999@gmail.com");
		//sc.modificarCliente(29l, "Rodrigo", "42478211", "Calizaya", "rodrigo111999@gmail.com");
		
		//sc.agregarTarjeta(4l, "5642537", "Matercard");
	
		for (TarjetaDeCredito t: sc.listarTarjetas(4l)) {
			System.out.println("Tarjeta: "+ t.toString());
		}
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
