package ar.unrn.tp.modelo;

public class ProductoSeleccionado {

	private Producto producto;
	private int cantidad;
	
	public ProductoSeleccionado(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public double calcularPrecio() {
		return this.producto.getPrecio() * this.cantidad;
	}

}
