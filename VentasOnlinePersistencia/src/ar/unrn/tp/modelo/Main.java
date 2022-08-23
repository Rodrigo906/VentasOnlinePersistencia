package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.unrn.tp.servicio_tarjeta.VerificacionDeTarjetaWeb;

public class Main {

	public static void main(String[] args) {
		/*
		Producto p1 = new Producto("1", "zapatilla", "Calzado", "Acne", 5000);
		Producto p2 = new Producto("2", "pantalon", "Ropa deportiva", "Levis", 2500);
		
		ProductoSeleccionado ps1 = new ProductoSeleccionado(p1, 1);
		ProductoSeleccionado ps2 = new ProductoSeleccionado(p2, 1);
		
		Promocion promoDeProducto = new PromocionDeProducto(LocalDate.of(2022, 8, 2), LocalDate.of(2022, 8, 10), "Acne");
		
		Promocion promoDeCompra = new PromocionDeCompra(LocalDate.of(2022, 8, 1), LocalDate.of(2022, 8, 13), "MemeCard");
		
		GestorPromociones gestorPromos = new GestorPromociones();	
		gestorPromos.agregarPromocionProducto(promoDeProducto);
		gestorPromos.agregarPromocionCompra(promoDeCompra);
		
		Cliente cliente = new Cliente(41478211, "Rodrigo", "Calizaya", "rodrigocalizaya111999@gmail.com");
		VerificarTarjeta verificarTarjeta = new VerificacionDeTarjetaWeb();
		TarjetaDeCredito tarjeta = new TarjetaDeCredito(12345, "MemeCard");
		
		Carrito carrito = new Carrito(gestorPromos, verificarTarjeta, cliente);
		carrito.agregarProducto(ps1);
		carrito.agregarProducto(ps2);
		
		Venta venta = carrito.realizarVenta(tarjeta);
		System.out.println(venta.toString());
		*/
		
		/* Prueba de fechas
		ConversorDeFechas conv = new ConversorDeFechas();
		
		Date fechaDate = conv.convertirADate(LocalDate.now());
		System.out.println("Convertir de LocalDate a Date: " + fechaDate);
		
		LocalDate fechaLocal = conv.convertirALocalDate(new Date());
		System.out.println("Convertir de Date a LocalDate: " + fechaLocal);
		
		LocalDateTime fechaDateTime = conv.convertirALocalDateTime(new Date());
		System.out.println("Convertir Date a LocalDateTime: " + fechaDateTime);
		
		fechaDate = conv.convertirADate(LocalDateTime.now());
		System.out.println("Convertir LocalDateTime a Date: " + fechaDate);
		*/
		
		/*
		Producto p1 = new Producto("1", "zapatilla", "Calzado", "Acne", 5000);
		Producto p2 = new Producto("2", "pantalon", "Ropa deportiva", "Comarca", 2500);
		
		ProductoSeleccionado ps1 = new ProductoSeleccionado(p1, 1);
		ProductoSeleccionado ps2 = new ProductoSeleccionado(p2, 1);
		
		LocalDate fechaActual = LocalDate.now();
		Promocion promoDeCompra = new PromocionDeCompra(fechaActual.minusDays(2), fechaActual.plusDays(3), "MemeCard");
		Promocion promoDeProducto = new PromocionDeProducto(fechaActual.minusDays(2), fechaActual.plusDays(3), "Comarca");
		
		GestorPromociones gestorPromos = new GestorPromociones();	
		gestorPromos.agregarPromocionCompra(promoDeCompra);
		gestorPromos.agregarPromocionProducto(promoDeProducto);
		
		Cliente cliente = new Cliente(41478211, "Rodrigo", "Calizaya", "rodrigocalizaya111999@gmail.com");
		VerificarTarjeta verificarTarjeta = new VerificacionDeTarjetaWeb();
		TarjetaDeCredito tarjeta = new TarjetaDeCredito(12345, "MemeCard");
		
		Carrito carrito = new Carrito(gestorPromos, verificarTarjeta, cliente);
		carrito.agregarProducto(ps1);
		carrito.agregarProducto(ps2);
		Venta venta = carrito.realizarVenta(tarjeta);
		System.out.println(venta.toString());
		*/
		
		
		LocalDate fechaActual = LocalDate.now();
		System.out.println("Dias");
		System.out.println(fechaActual.minusDays(3) +"-"+ fechaActual.minusDays(4));
		System.out.println(fechaActual.minusDays(4) +"-"+ fechaActual.plusDays(3));
		Promocion promoProducto1 = new PromocionDeProducto(fechaActual.minusDays(6), fechaActual.minusDays(4), "Acne", 0.05f);
		Promocion promoProducto2 = new PromocionDeProducto(fechaActual.minusDays(3), fechaActual.plusDays(3), "Acne", 0.05f);
		
		GestorPromociones gestorPromos = new GestorPromociones();	
		gestorPromos.agregarPromocionProducto(promoProducto1);
		gestorPromos.agregarPromocionProducto(promoProducto2);
	}
}
