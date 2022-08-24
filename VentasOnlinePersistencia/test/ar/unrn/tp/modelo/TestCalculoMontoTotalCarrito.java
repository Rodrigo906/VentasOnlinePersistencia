package ar.unrn.tp.modelo;

import java.time.LocalDate;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.tp.servicio_tarjeta.VerificacionDeTarjetaWeb;

public class TestCalculoMontoTotalCarrito {

	@Test
	public void montoTotalConDescuentoCaducado() {
		Producto p1 = new Producto("1", "zapatilla", "Calzado", "Acne", 5000);
		Producto p2 = new Producto("2", "pantalon", "Ropa deportiva", "Levis", 2500);
		
		ProductoSeleccionado ps1 = new ProductoSeleccionado(p1, 1);
		ProductoSeleccionado ps2 = new ProductoSeleccionado(p2, 1);
		
		LocalDate fechaActual = LocalDate.now();
		Promocion promoDeProducto = new PromocionDeProducto(fechaActual.minusDays(5), fechaActual.minusDays(2), "Acne", 0.05f);
		
		GestorPromociones gestorPromos = new GestorPromociones();	
		gestorPromos.agregarPromocionProducto(promoDeProducto);
		
		Cliente cliente = new Cliente(41478211, "Rodrigo", "Calizaya", "rodrigocalizaya111999@gmail.com");
		VerificarTarjeta verificarTarjeta = new VerificacionDeTarjetaWeb();
		TarjetaDeCredito tarjeta = new TarjetaDeCredito(12345, "MemeCard");
		
		Carrito carrito = new Carrito(gestorPromos, verificarTarjeta, cliente);
		carrito.agregarProducto(ps1);
		carrito.agregarProducto(ps2);
		double montoTotal = gestorPromos.calcularMontoTotal(tarjeta.getMarca(), carrito.getListadoProductos());
		
		Assert.assertEquals(7500, montoTotal, 1);
	}
	
	@Test
	public void montoTotalConDescuentoAcme() {
		Producto p1 = new Producto("1", "zapatilla", "Calzado", "Acne", 5000);
		Producto p2 = new Producto("2", "pantalon", "Ropa deportiva", "Levis", 2500);
		
		ProductoSeleccionado ps1 = new ProductoSeleccionado(p1, 1);
		ProductoSeleccionado ps2 = new ProductoSeleccionado(p2, 1);
		
		LocalDate fechaActual = LocalDate.now();
		Promocion promoDeProducto = new PromocionDeProducto(fechaActual.minusDays(2), fechaActual.plusDays(3), "Acne", 0.05f);
		
		GestorPromociones gestorPromos = new GestorPromociones();	
		gestorPromos.agregarPromocionProducto(promoDeProducto);
		
		Cliente cliente = new Cliente(41478211, "Rodrigo", "Calizaya", "rodrigocalizaya111999@gmail.com");
		VerificarTarjeta verificarTarjeta = new VerificacionDeTarjetaWeb();
		TarjetaDeCredito tarjeta = new TarjetaDeCredito(12345, "MemeCard");
		
		Carrito carrito = new Carrito(gestorPromos, verificarTarjeta, cliente);
		carrito.agregarProducto(ps1);
		carrito.agregarProducto(ps2);
		double montoTotal = gestorPromos.calcularMontoTotal(tarjeta.getMarca(), carrito.getListadoProductos());
		
		Assert.assertEquals(7250, montoTotal, 1);
	}
	
	@Test
	public void montoTotalConDescuentoCompra() {
		Producto p1 = new Producto("1", "zapatilla", "Calzado", "Acne", 5000);
		Producto p2 = new Producto("2", "pantalon", "Ropa deportiva", "Levis", 2500);
		
		ProductoSeleccionado ps1 = new ProductoSeleccionado(p1, 1);
		ProductoSeleccionado ps2 = new ProductoSeleccionado(p2, 1);
		
		LocalDate fechaActual = LocalDate.now();
		Promocion promoDeCompra = new PromocionDeCompra(fechaActual.minusDays(2), fechaActual.plusDays(3), "Visa", 0.08f);
		
		GestorPromociones gestorPromos = new GestorPromociones();	
		gestorPromos.agregarPromocionCompra(promoDeCompra);
		
		Cliente cliente = new Cliente(41478211, "Rodrigo", "Calizaya", "rodrigocalizaya111999@gmail.com");
		VerificarTarjeta verificarTarjeta = new VerificacionDeTarjetaWeb();
		TarjetaDeCredito tarjeta = new TarjetaDeCredito(12345, "Visa");
		
		Carrito carrito = new Carrito(gestorPromos, verificarTarjeta, cliente);
		carrito.agregarProducto(ps1);
		carrito.agregarProducto(ps2);
		double montoTotal = gestorPromos.calcularMontoTotal(tarjeta.getMarca(), carrito.getListadoProductos());
		
		Assert.assertEquals(6900, montoTotal, 1);
	}
	
	@Test
	public void montoTotalConDescuentoCompraYProducto() {
		Producto p1 = new Producto("1", "zapatilla", "Calzado", "Acne", 5000);
		Producto p2 = new Producto("2", "pantalon", "Ropa deportiva", "Comarca", 2500);
		
		ProductoSeleccionado ps1 = new ProductoSeleccionado(p1, 1);
		ProductoSeleccionado ps2 = new ProductoSeleccionado(p2, 1);
		
		LocalDate fechaActual = LocalDate.now();
		Promocion promoDeCompra = new PromocionDeCompra(fechaActual.minusDays(2), fechaActual.plusDays(3), "MemeCard", 0.08f);
		Promocion promoDeProducto = new PromocionDeProducto(fechaActual.minusDays(2), fechaActual.plusDays(3), "Comarca", 0.05f);
		
		GestorPromociones gestorPromos = new GestorPromociones();	
		gestorPromos.agregarPromocionCompra(promoDeCompra);
		gestorPromos.agregarPromocionProducto(promoDeProducto);
		
		Cliente cliente = new Cliente(41478211, "Rodrigo", "Calizaya", "rodrigocalizaya111999@gmail.com");
		VerificarTarjeta verificarTarjeta = new VerificacionDeTarjetaWeb();
		TarjetaDeCredito tarjeta = new TarjetaDeCredito(12345, "MemeCard");
		
		Carrito carrito = new Carrito(gestorPromos, verificarTarjeta, cliente);
		carrito.agregarProducto(ps1);
		carrito.agregarProducto(ps2);
		double montoTotal = gestorPromos.calcularMontoTotal(tarjeta.getMarca(), carrito.getListadoProductos());
		
		Assert.assertEquals(6785, montoTotal, 1);
	}
	
	@Test
	public void generacionDeVentaCorrecta() {
		
		Producto p1 = new Producto("1", "zapatilla", "Calzado", "Acne", 5000);
		Producto p2 = new Producto("2", "pantalon", "Ropa deportiva", "Levis", 2500);
		
		ProductoSeleccionado ps1 = new ProductoSeleccionado(p1, 1);
		ProductoSeleccionado ps2 = new ProductoSeleccionado(p2, 1);
		
		LocalDate fechaActual = LocalDate.now();
		Promocion promoDeProducto = new PromocionDeProducto(fechaActual.minusDays(5), fechaActual.minusDays(2), "Acne", 0.05f);
		
		GestorPromociones gestorPromos = new GestorPromociones();	
		gestorPromos.agregarPromocionProducto(promoDeProducto);
		
		Cliente cliente = new Cliente(41478211, "Rodrigo", "Calizaya", "rodrigocalizaya111999@gmail.com");
		VerificarTarjeta verificarTarjeta = new VerificacionDeTarjetaWeb();
		TarjetaDeCredito tarjeta = new TarjetaDeCredito(12345, "MemeCard");
		
		Carrito carrito = new Carrito(gestorPromos, verificarTarjeta, cliente);
		carrito.agregarProducto(ps1);
		carrito.agregarProducto(ps2);
		
		Assert.assertNotNull(carrito.realizarVenta(tarjeta));
	}
	
	
	
	
	
	
	
	
	
	
	
}
