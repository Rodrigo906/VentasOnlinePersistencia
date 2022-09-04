package ar.unrn.tp.main;

import java.util.HashMap;
import java.util.Map;

import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.jpa.servicios.ServicioVenta;
import ar.unrn.tp.modelo.Venta;

public class MainServicioVenta {
	
	public static void main(String[] args) {
		
		VentaService vs = new ServicioVenta();
		
		//Probar con mas productos una ves que se puedan cargar
		Map<Long, Integer> productos = new HashMap<Long, Integer>();
		productos.put(11L, 1);
		productos.put(22L, 2);
		
		System.out.println(vs.calcularMonto(productos, 5L));
		
		//vs.realizarVenta(4L, productos, 5L);
		
		for (Venta v : vs.ventas()) {
			System.out.println("---------------------------------------------------------");
			System.out.println(v.toString());
		}
		
	}

}
