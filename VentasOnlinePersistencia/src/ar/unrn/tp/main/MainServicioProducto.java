package ar.unrn.tp.main;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.jpa.servicios.ServicioProducto;
import ar.unrn.tp.modelo.Producto;

public class MainServicioProducto {
	
	public static void main(String[] args) {
		
		ProductoService ps = new ServicioProducto("$objectdb/db/p2.odb");
		
		ps.crearProducto("3", "Remera", 1500, "Ropa deportiva", "Levis");
			
		
		//ps.modificarProducto(22L, "2", "Pantalon", 2500, "Ropa deportiva", "Levis");
		
		for (Producto p : ps.listarProductos()) {
			System.out.println(p.toString());
		}
		
		
	
	}
}
