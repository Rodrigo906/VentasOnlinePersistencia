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
		
		ClienteService sc = new ServicioCliente();
		//sc.crearCliente("Rodrigo", "Calizaya", "41478211", "rodrigo111999@gmail.com");
		
		//sc.modificarCliente(4l, "Cristian", "41478211", "Garnica", "rodrigo111999@gmail.com");
		//sc.modificarCliente(4l, "Rodrigo", "41478211", "Calizaya", "rodrigo111999@gmail.com");
		
		//sc.agregarTarjeta(4l, "12345678", "Visa");
	
		for (TarjetaDeCredito t: sc.listarTarjetas(4l)) {
			System.out.println("Tarjeta: "+ t.toString());
		}
		
	}
}
