package ar.unrn.tp.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Carrito {

	private ArrayList<ProductoSeleccionado> listaProductos;
	private VerificarTarjeta verificarTarjeta;
	private Cliente cliente;
	private GestorPromociones gestorPromociones;
	
	
	public Carrito(GestorPromociones gestorPromociones, VerificarTarjeta verificarTarjeta, Cliente cliente) {
		this.listaProductos = new ArrayList<ProductoSeleccionado>();
		this.gestorPromociones = gestorPromociones;
		this.verificarTarjeta = verificarTarjeta;
		this.cliente = cliente;
	}
	public boolean agregarProducto (ProductoSeleccionado producto) {
		return this.listaProductos.add(producto);
	}
	
	public boolean quitarProducto(ProductoSeleccionado productoQuitar) {
		return listaProductos.remove(productoQuitar);
	}
	
	public ArrayList<ProductoSeleccionado> getListadoProductos() {
		return this.listaProductos;
	}
	
	public Venta realizarVenta(TarjetaDeCredito tarjeta) {
		Venta venta = null;
		int numeroTarjeta = tarjeta.getNumero();
		String marca = tarjeta.getMarca();
		
		if (verificarTarjeta.tieneFondosSuficientes(numeroTarjeta, marca) &&
				verificarTarjeta.estaActiva(numeroTarjeta, marca))
			{
				LocalDateTime fechaActual = LocalDateTime.now();
				double montoTotal = this.gestorPromociones.calcularMontoTotal(marca, this.listaProductos);
				venta = new Venta(fechaActual, this.cliente, this.getListadoProductos(), montoTotal);
			}
		//Agregar exepcion en caso de que las verificaciones sean incorrectas
		return venta;
	}
	
	
	
}
