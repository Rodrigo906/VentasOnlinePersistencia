package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.TarjetaDeCredito;

public interface ClienteService {

	// validar que el dni no se repita
	void crearCliente(String nombre, String apellido, String dni, String email);
	// validar que sea un cliente existente
	void modificarCliente(Long idCliente, String nombre, String dni, String apellido, String email);
	// validar que sea un cliente existente
	void agregarTarjeta(Long idCliente, String nro, String marca);

	 //Devuelve las tarjetas de un cliente espec�fico
	 List<TarjetaDeCredito> listarTarjetas(Long idCliente);
	 
	 List<Cliente> obtenerClientes();
}
