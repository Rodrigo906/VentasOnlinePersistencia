package ar.unrn.tp.main;

import java.time.LocalDate;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.jpa.servicios.ServicioDescuento;
import ar.unrn.tp.jpa.servicios.ServicioGestorPromociones;
import ar.unrn.tp.modelo.GestorPromociones;

public class MainServicioDescuento {

	public static void main(String[] args) {
		
		DescuentoService sd = new ServicioDescuento("$objectdb/db/p2.odb");
		//DescuentoService sd = new ServicioDescuento("ORM");
		ServicioGestorPromociones sgp = new ServicioGestorPromociones("$objectdb/db/p2.odb");
		sgp.crearGestor();
		
		LocalDate fechaDesde = LocalDate.of(2022, 9, 16);
		LocalDate fechaHasta = LocalDate.of(2022, 9, 25);

		sd.crearDescuentoSobreTotal("Visa", fechaDesde, fechaHasta, 0.08f);
		
		sd.crearDescuento("Levis", fechaDesde, fechaHasta, 0.05f);
		sd.crearDescuento("Nike", fechaDesde, fechaHasta, 0.05f);
		
	}
	
}
