package ar.unrn.tp.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.jpa.servicios.ServicioCliente;
import ar.unrn.tp.jpa.servicios.ServicioDescuento;
import ar.unrn.tp.jpa.servicios.MyService;
import ar.unrn.tp.jpa.servicios.ServicioProducto;
import ar.unrn.tp.jpa.servicios.ServicioVenta;
import ar.unrn.tp.modelo.Venta;

public class MainServicioVenta {
	
	public static void main(String[] args) {
		
		/*ServicioGestorPromociones sgp = new ServicioGestorPromociones("ORM");
		sgp.crearGestor();
		*//*
		DescuentoService sd = new ServicioDescuento("ORM");
		LocalDate fechaDesde = LocalDate.of(2022, 9, 11);
		LocalDate fechaHasta = LocalDate.of(2022, 9, 16);
		sd.crearDescuentoSobreTotal("Macro", fechaDesde, fechaHasta, 0.08f);
		sd.crearDescuento("Levis", fechaDesde, fechaHasta, 0.05f);
		
		ProductoService ps = new ServicioProducto("ORM");
		ps.crearProducto("1", "Remera", 1500, "Ropa deportiva", "Levis");
		ps.crearProducto("2", "Pantalon", 2000, "Ropa de vestir", "Brand");
		
		ClienteService sc = new ServicioCliente("ORM");
		sc.crearCliente("Cristian", "Garnica", "42478211", "rodrigo111999@gmail.com");
		sc.agregarTarjeta(6l, "5642537", "Matercard");
		
		VentaService vs = new ServicioVenta("$objectdb/db/p2.odb");
		VentaService vs = new ServicioVenta("ORM");
		
		//System.out.println(vs.calcularMonto(productos, 5L));
		//System.out.println(vs.calcularMonto(productos, 7L));
		
		try {
		for (Venta v : vs.ventas()) {
			System.out.println("---------------------------------------------------------");
			System.out.println(v.toString());
		}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
		
		
		
		//PRUEBA DE CONCURRENCIA
		VentaService vs = new ServicioVenta("ORM");
		
		Map<Long, Integer> productos = new HashMap<Long, Integer>();
		productos.put(7L, 1);
		productos.put(8L, 2);
		
		vs.realizarVenta(1L, productos, 2L);
	
	}

}
