package ar.unrn.tp.main;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.jpa.servicios.ServicioProducto;

public class MainServicioProducto {
	
	public static void main(String[] args) {
		ProductoService ps = new ServicioProducto();
		ps.crearProducto("1", "zapatilla", 5000, "Calzado", "Acne");
		
		
	
	}
}
