package ar.unrn.tp.main;

import java.time.LocalDate;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.jpa.servicios.ServicioDescuento;

public class MainServicioDescuento {

	public static void main(String[] args) {
		
		DescuentoService sd = new ServicioDescuento("$objectdb/db/p2.odb");
		
		LocalDate fechaDesde = LocalDate.of(2022, 9, 11);
		LocalDate fechaHasta = LocalDate.of(2022, 9, 16);
		//sd.crearDescuentoSobreTotal("Macro", fechaDesde, fechaHasta, 0.08f);
		
		//sd.crearDescuento("Levis", fechaDesde, fechaHasta, 0.05f);
		
	}
	
}
