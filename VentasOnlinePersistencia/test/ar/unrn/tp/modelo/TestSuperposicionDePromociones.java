package ar.unrn.tp.modelo;

import java.time.LocalDate;

import org.junit.Test;

public class TestSuperposicionDePromociones {

	@Test(expected = RuntimeException.class)
	public void promoProductoSuperpuesta() {
		LocalDate fechaActual = LocalDate.now();
		GestorPromociones gestor = new GestorPromociones();
		Promocion promoProducto1 = new PromocionDeProducto(fechaActual.minusDays(6), fechaActual.minusDays(4), "Acne", 0.05f);
		Promocion promoProducto2 = new PromocionDeProducto(fechaActual.minusDays(5), fechaActual.plusDays(3), "Acne", 0.05f);
		gestor.agregarPromocionProducto(promoProducto1);
		gestor.agregarPromocionProducto(promoProducto2);
	}
	
	@Test(expected = RuntimeException.class)
	public void promoProductoSuperpuestaEnExtremos() {
		LocalDate fechaActual = LocalDate.now();
		GestorPromociones gestor = new GestorPromociones();
		Promocion promoProducto1 = new PromocionDeProducto(fechaActual.minusDays(6), fechaActual.minusDays(4), "Acne",0.05f);
		Promocion promoProducto2 = new PromocionDeProducto(fechaActual.minusDays(4), fechaActual.plusDays(3), "Acne", 0.05f);
		gestor.agregarPromocionProducto(promoProducto1);
		gestor.agregarPromocionProducto(promoProducto2);
	}
	
	@Test(expected = RuntimeException.class)
	public void promoCompraSuperpuesta() {
		LocalDate fechaActual = LocalDate.now();
		GestorPromociones gestor = new GestorPromociones();
		Promocion promoCompra1 = new PromocionDeCompra(fechaActual.minusDays(6), fechaActual.minusDays(4), "MemeCard", 0.08f);
		Promocion promoCompra2 = new PromocionDeCompra(fechaActual.minusDays(5), fechaActual.plusDays(3), "MemeCard", 0.08f);
		gestor.agregarPromocionProducto(promoCompra1);
		gestor.agregarPromocionProducto(promoCompra2);
	}
	
	@Test(expected = RuntimeException.class)
	public void promoCompraSuperpuestaExtremos() {
		LocalDate fechaActual = LocalDate.now();
		GestorPromociones gestor = new GestorPromociones();
		Promocion promoCompra1 = new PromocionDeCompra(fechaActual.minusDays(6), fechaActual.minusDays(4), "MemeCard", 0.08f);
		Promocion promoCompra2 = new PromocionDeCompra(fechaActual.minusDays(4), fechaActual.plusDays(3), "MemeCard", 0.08f);
		gestor.agregarPromocionProducto(promoCompra1);
		gestor.agregarPromocionProducto(promoCompra2);
	}
	
}
