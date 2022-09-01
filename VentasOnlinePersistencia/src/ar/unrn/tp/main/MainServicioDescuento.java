package ar.unrn.tp.main;

import java.time.LocalDate;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.jpa.servicios.ServicioDescuento;

public class MainServicioDescuento {

	public static void main(String[] args) {
		
		DescuentoService sd = new ServicioDescuento();
		LocalDate fechaDesde;
		LocalDate fechaHasta;
		//sd.crearGestor();
		/*
		fechaDesde = LocalDate.of(2022, 8, 31);
		fechaHasta = LocalDate.of(2022, 9, 15);
		sd.crearDescuentoSobreTotal("Visa", fechaDesde, fechaHasta, 0.08f);
		*/
		
		fechaDesde = LocalDate.of(2022, 8, 1);
		fechaHasta = LocalDate.of(2022, 9, 13);
		sd.crearDescuento("Matarazzo", fechaDesde, fechaHasta, 0.05f);
	}
}
