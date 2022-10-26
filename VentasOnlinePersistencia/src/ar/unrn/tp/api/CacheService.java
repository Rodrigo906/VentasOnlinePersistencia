package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.Venta;

public interface CacheService {

	void registrarVenta(Venta venta, long idCliente);
	
	void cargarVentas(List<Venta> ventas, long idCliente);
	
	List<Venta> obtenerVentas(long idCliente);
	
}
